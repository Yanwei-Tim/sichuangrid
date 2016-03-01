/* Copyright (c) 2006-2010 by OpenLayers Contributors (see authors.txt for 
 * full list of contributors). Published under the Clear BSD license.  
 * See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/* 
 * @requires OpenLayers/BaseTypes.js
 * @requires OpenLayers/Lang/en.js
 * @requires OpenLayers/Console.js
 */ 

(function() {

    
    /**
     * Cache for the script location returned from
     * OpenLayers._getScriptLocation
     */
    var scriptLocation;
    
    /**
     * Namespace: OpenLayers
     * The OpenLayers object provides a namespace for all things OpenLayers
     */
    window.OpenLayers = {
        
        /**
         * Property: _scriptName
         * {String} Relative path of this script.
         */
        _scriptName: "OpenLayers-",

        /**
         * Function: _getScriptLocation
         * Return the path to this script.
         *
         * Returns:
         * {String} Path to this script
         */
        _getScriptLocation: function () {
            if (scriptLocation != undefined) {
                return scriptLocation;
            }
            scriptLocation = "";            
            var isOL = new RegExp("(^|(.*?\\/))(" + OpenLayers._scriptName + ")(\\?|)");
         
            var scripts = document.getElementsByTagName('script');
            for (var i=0, len=scripts.length; i<len; i++) {
                var src = scripts[i].getAttribute('src');
                if (src) {
                    var match = src.match(isOL);
                    if(match) {
                        scriptLocation = match[1];
                        break;
                    }
                }
            }
            return scriptLocation;
        }
    };
})();

