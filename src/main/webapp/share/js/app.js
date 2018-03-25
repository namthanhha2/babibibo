/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = {
  parse : function (s) {
        var match = [];
        var scripts = [];
        while (match) {
            match = s.match(/<script([^>]*)>([\s\S]*?)<\/script>/i);
            if (!match) {
                break;
            }
            if (match[1]) {
                var parseArray = match[1].match(/src=(['"]?)([^"']*)\1/i);
                if (parseArray) {
                    // remove a dojo.js or dojo.js.uncompressed.js from remoteScripts
                    // we declare all files with dojo.js as bad, regardless of folder
                    var tmp2 = parseArray[2].search(/.*(\bdojo\b(?:\.uncompressed)?\.js)$/);
                    if (tmp2 > -1) {
                        //this.log("Security note! inhibit:"+attr[2]+" from  beeing loaded again.");
                    } else {
                        var src = parseArray[2];
//                        $.ajax({
//                            url : src,
//                            dataType : 'script'
//                        });
                    }

                }
                // [ Datbt
                // bo qua nhung doan script co type = dojo/method hoac dojo/connect
                parseArray = match[1].match(/type=(['"]dojo\/method)(["'])/);
                if (parseArray) {
                    match[2] = "";
                }
                parseArray = match[1].match(/type=(['"]dojo\/connect)(["'])/);
                if (parseArray) {
                    match[2] = "";
                }

                // ] Datbt
            }
            if (match[2]) {
                // strip out all djConfig variables from script tags nodeValue
                // this is ABSOLUTLY needed as reinitialize djConfig after dojo is initialised
                // makes a dissaster greater than Titanic, update remove writeIncludes() to
                var sc = match[2].replace(/(?:var )?\bdjConfig\b(?:[\s]*=[\s]*\{[^}]+\}|\.[\w]*[\s]*=[\s]*[^;\n]*)?;?|dojo\.hostenv\.writeIncludes\(\s*\);?/g, "");
                if (!sc) {
                    continue;
                }

                // cut out all dojo.require (...) calls, if we have execute
                // scripts false widgets dont get there require calls
                // does suck out possible widgetpackage registration as well
                //[Datbt không bỏ những script như require...
                //                    tmp = [];
                //                    while(tmp){
                //                        tmp = sc.match(/dojo\.(?:(?:require(?:After)?(?:If)?)|(?:widget\.(?:manager\.)?registerWidgetPackage)|(?:(?:hostenv\.)?setModulePrefix))\((['"]).*?\1\)\s*;?/);
                //                        if(!tmp){
                //                            break;
                //                        }
                //                        sc = sc.replace(tmp[0], "");
                //                    }
                //]Datbt không bỏ những script như require...
                scripts.push(sc);
            }
            s = s.replace(/<script[^>]*>[\s\S]*?<\/script>/i, "");
        }

        return {
            text: s,
            scripts: scripts
        };
    },
    execScript: function (scripts) {
        eval(scripts.join(""));
    },
    parseAndExecScript : function (response){
        var data = this.parse(response);
        this.execScript(data.scripts);
    }
};

