(function (c) {
    function r(f) {
        f = f ? f : window.event ? window.event : null;
        return f.srcElement ? f.srcElement : f.target
    }
    function h(f) {
        f.preventDefault();
        f.stopPropagation()
    }
    if (typeof WUC == "undefined") window.WUC = {};
    Function.prototype.bind2 = function (f) {
        var m = this;
        return function () {
            m.apply(f, arguments)
        }
    };
    if (c.browser.msie) h = function (f) {
        f.cancelBubble = true;
        f.returnValue = false
    };
    WUC.searchMember = function () {
        function f(a) {
            d = c.extend(u, a);
            v = new m
        }
        function m() {
            this.test = "test";
            this.baseBox = c("#" + d.baseBox);
            this.show_bt = c("." + d.showSearch_bt);
            this.searchInput = c("." + d.searchInput);
            this.searchBox = c("." + d.srearchBox);
            this.resultsBox = c("." + d.resultsBox);
            this.searchBar = c("." + d.searchBar);
            this.clearBar = c("." + d.clearBar);
            this.registersTitle = c("." + d.userGroups_title).eq(0);
            this.registersList = c("." + d.userGroups_list).eq(0);
            this.registersNumBox = this.registersTitle.find("span").eq(0);
            this.registersNum = 0;
            this.visitorsTitle = c("." + d.userGroups_title).eq(1);
            this.visitorsList = c("." + d.userGroups_list).eq(1);
            this.visitorsNumBox = this.visitorsTitle.find("span").eq(0);
            this.visitorsNum = 0;
            this.listCtrlMenu = c("#" + d.listCtrlMenu);
            this.wuc = this.noResults = null;
            this.bindEvent()
        }
        var v = null,
            p = null,
            w = -1,
            j = null,
            s = "",
            d, u = {
                baseBox: "ucChatRoom",
                srearchBox: "searchMemberList",
                resultsBox: "searchResult",
                searchBar: "searchInput",
                searchInput: "searchInput input",
                clearBar: "searchInput p a",
                showSearch_bt: "groupTitle a",
                userGroups_title: ".searchResult h3",
                userGroups_list: ".searchResult ul",
                listCtrlMenu: "ucChatRoom .dropMenu",
                listCtrlMenuLink: "ucChatRoom .dropMenu a",
                tipsTitle: "\u8bf7\u8f93\u5165\u6635\u79f0",
                delay: 400,
                registered: "1",
                max: 10,
                autoFill: false,
                stopClass: "",
                onItemSelect: function () {},
                onSearch: function () {},
                getOutObj: function () {}
            },
            i = {
                BACKSPACE: 8,
                TAB: 9,
                RETURN: 13,
                ESC: 27,
                LEFT: 37,
                UP: 38,
                RIGHT: 39,
                DOWN: 40,
                DELETE: 46,
                HOME: 36,
                END: 35,
                PAGEUP: 33,
                PAGEDOWN: 34,
                INSERT: 45,
                SPACE: 32
            };
        m.prototype = {
                testfn: function () {
                    alert(this.test)
                },
                bindEvent: function () {
                    var a = this;
                    this.show_bt.click(function (b) {
                        h(b);
                        a.displaySearch()
                    });
                    this.clearBar.click(function () {
                        a.searchInput.val("");
                        a.resultsBox.hide();
                        c(this).hide();
                        return false
                    });
                    this.searchInput.focus(function (b) {
                        a.clearDefault();
                        h(b)
                    });
                    this.searchInput.keyup(function (b) {
                        a.checkKeyCode(b);
                        h(b)
                    });
                    this.searchInput.bind("paste", function () {
                        a.searchInput.keyup()
                    });
                    this.registersTitle.click(function (b) {
                        c(this).toggleClass("dropRight");
                        a.registersList.toggle();
                        h(b)
                    });
                    this.visitorsTitle.click(function (b) {
                        c(this).toggleClass("dropRight");
                        a.visitorsList.toggle();
                        h(b)
                    });
                    c(document).bind("mousedown", function (b) {
                        a.bindDocumentClick(b);
                        b.stopPropagation()
                    })
                },
                displaySearch: function () {
                    obj_x = this;
                    obj_x.show_bt.unbind("click");
                    obj_x.resultsBox.hide();
                    obj_x.searchBox.show();
                    obj_x.searchInput.val(d.tipsTitle)
                },
                hideSearch: function () {
                    obj_x = this;
                    this.searchBox.hide();
                    this.clearBar.hide();
                    this.listCtrlMenu.hide();
                    this.show_bt.one("click", function () {
                        obj_x.displaySearch();
                        return false
                    })
                },
                bindDocumentClick: function (a) {
                    a = r(a);
                    var b = true;
                    if (a == this.show_bt.get(0) || a == this.clearBar.get(0)) b = false;
                    for (; a.offsetParent && b;) {
                        if (a == this.searchBox.get(0) || a == this.listCtrlMenu.get(0)) b = false;
                        a = a.offsetParent
                    }
                    b && this.hideSearch()
                },
                clearDefault: function () {
                    this.searchInput.val() == d.tipsTitle && this.searchInput.val("")
                },
                checkBindKeyUp: function (a) {
                    var b = this;
                    a.ctrlKey && a.shiftKey || a.ctrlKey && a.keycode == i.Space ? b.searchInput.unbind("keyup") : b.searchInput.keyup(function (e) {
                        b.checkKeyCode(e);
                        h(event)
                    })
                },
                checkKeyCode: function (a) {
                    eventObj = this;
                    $input = this.searchInput;
                    j = a.keyCode;
                    switch (a.keyCode) {
                    case i.UP:
                        a.preventDefault();
                        break;
                    case i.DOWN:
                        a.preventDefault();
                        break;
                    case i.TAB:
                        break;
                    case i.RETURN:
                        this.onChange();
                        break;
                    default:
                        w = -1;
                        p && clearTimeout(p);
                        p = setTimeout(function () {
                            eventObj.onChange()
                        }, d.delay);
                        break
                    }
                },
                onChange: function () {
                    $results = this.resultsBox;
                    $input = this.searchInput;
                    if (!(j == i.DELETE || j > i.BACKSPACE && j < 32 && j != i.RETURN)) {
                        this.clearBar.show();
                        this.resultsBox.show();
                        var a = new String($input.val()),
                            b = a.replace(/(^[\s\u3000]*)|([\s\u3000]*$)/g, "");
                        b.length === 0 && a.length > 0 || (a = b);
                        if (a.length === 0) {
                                this.clearBar.hide();
                                return $results.hide()
                            }
                        s = a;
                        b = this.requestData(a);
                        this.receiveData(a, b)
                    }
                },
                requestData: function (a) {
                    if (typeof d.onSearch === "function") return d.onSearch(a)
                },
                receiveData: function (a, b) {
                    $input = this.searchInput;
                    b ? this.dataToDom(b) : this.noMatchData()
                },
                noMatchData: function () {
                    this.hideGroups();
                    if (this.noResults == null) {
                        this.noResults = c("<ul/>");
                        this.noResults.html(c("<li/>").html("\u6ca1\u6709\u5339\u914d\u7684\u7ed3\u679c"));
                        this.noResults.appendTo($results)
                    } else this.noResults.show()
                },
                dataToDom: function (a) {
                    this.clearGroups();
                    this.resultsBox.show();
                    a instanceof Array || (a = [a]);
                    if (a.length == 0) this.noMatchData();
                    else {
                        this.noResults && this.noResults.hide();
                        this.showGroups();
                        for (var b = 0, e = a.length; b < e; b++) this.selectGroup(a[b])
                    }
                },
                selectGroup: function (a) {
                    var b;
                    if (a.group == d.registered) {
                        b = this.registersList;
                        this.registersNumBox.html("(" + ++this.registersNum + ")");
                        this.visitorsNumBox.html("(" + this.visitorsNum + ")")
                    } else {
                        b = this.visitorsList;
                        this.registersNumBox.html("(" + this.registersNum + ")");
                        this.visitorsNumBox.html("(" + ++this.visitorsNum + ")")
                    }
                    b.append(this.constructLI(a))
                },
                constructLI: function (a) {
                    if (this.wuc == null && typeof d.getOutObj == "function") this.wuc = d.getOutObj();
                    var b = this.checkBlock(a.username),
                        e = c("<li/>"),
                        n = this;
                    e.hover(function () {
                            c(this).addClass("listHover")
                        }, function () {
                            c(this).removeClass("listHover")
                        });
                    a = a.nick;
                    b.blockState ? e.html('<span></span><a href="javascript:void(0)" class="available">' + a + "</a>") : e.html('<a href="javascript:void(0)" class="available">' + a + "</a>");
                    e.click(function (g) {
                            g.preventDefault();
                            var o = c(this);
                            g = o.get(0);
                            var t = n.getLiMenuP(g);
                            b.showMenuFn.call(b.me, function (k) {
                                k.css({
                                    top: t.top + "px",
                                    left: t.left + "px"
                                });
                                k.show();
                                (function () {
                                    k.unbind("click");
                                    k.click(function (l) {
                                        h(l);
                                        k.unbind("click");
                                        l = r(l);
                                        var q = c(this).find("a");
                                        q.get(0);
                                        l == q.get(1) && o.find("span").length == 0 && o.find("a").eq(0).before("<span></span>");
                                        l == q.get(2) && o.find("span").eq(0).remove();
                                        c(this).hide()
                                    })
                                })()
                            })
                        });
                    return e
                },
                checkBlock: function (a) {
                    for (var b = 0, e = this.wuc.Base.curRoom.memberList; b < e.length; b++) if (a == e[b].username) return {
                        me: e[b],
                        blockState: e[b].isBlock,
                        showMenuFn: e[b].showCtrlMenu
                    }
                },
                getLiMenuP: function (a) {
                    for (var b = 0, e = 0; a.offsetParent;) {
                        b += a.offsetTop;
                        e += a.offsetLeft;
                        a = a.offsetParent
                    }
                    for (var n = a = 0, g = this.baseBox.get(0); g.offsetParent;) {
                        a += g.offsetTop;
                        n += g.offsetLeft;
                        g = g.offsetParent
                    }
                    b -= a;
                    e = e - n - 100;
                    return {
                        top: b,
                        left: e
                    }
                },
                showGroups: function () {
                    this.registersTitle.get(0).className = "dropDown";
                    this.visitorsTitle.get(0).className = "dropDown";
                    this.registersList.show();
                    this.registersTitle.show();
                    this.visitorsTitle.show();
                    this.visitorsList.show()
                },
                hideGroups: function () {
                    this.registersList.hide();
                    this.registersTitle.hide();
                    this.visitorsTitle.hide();
                    this.visitorsList.hide()
                },
                clearGroups: function () {
                    this.registersList.html("");
                    this.registersNum = 0;
                    this.visitorsList.html("");
                    this.visitorsNum = 0
                },
                selectItem: function (a) {
                    a || (a = document.createElement("li"));
                    var b = c(a).find("a").html();
                    s = b;
                    $input.val(b);
                    d.onItemSelect && setTimeout(function () {
                        d.onItemSelect(c(a), $input)
                    }, 1)
                }
            };
        return {
                buildSearchMember: f
            }
    }()
})(jQuery);
(function (b) {
    function k(e) {
        e = e ? e : window.event ? window.event : null;
        return e.srcElement ? e.srcElement : e.target
    }
    function l(e) {
        e.preventDefault();
        e.stopPropagation()
    }
    if (typeof WUC == "undefined") window.WUC = {};
    Function.prototype.bind2 = function (e) {
        var h = this;
        return function () {
            h.apply(e, arguments)
        }
    };
    if (b.browser.msie) l = function (e) {
        e.cancelBubble = true;
        e.returnValue = false
    };
    WUC.witchChatShape = function () {
        function e(a) {
            c = b.extend(m, a);
            f = new j
        }
        function h() {
            b("#miniChat .chatViewBg").show();
            f && f.showMini();
            b.cookie("crstatus", 2, {
                path: "/",
                expires: 365
            })
        }
        function n() {
            f && f.hideMini()
        }
        function o(a) {
            f && f.newMessage(a)
        }
        function p(a) {
            if (c && f) {
                c = b.extend(c, a);
                f.setDefaultNum()
            }
        }
        function q() {
            if (f) {
                f.unBindEvent();
                f = null
            }
        }
        function r() {
            f && f.IndentMini()
        }
        function j() {
            this.switchBt = b("." + c.switchBig_bt);
            this.miniBox = b("#" + c.miniChatBox);
            this.miniBar = b("." + c.miniBar);
            this.miniTitle = b("." + c.miniChatTitle);
            this.messageList = b("." + c.miniMessageList);
            this.shield_bt = b("#" + c.shield_bt);
            this.Indent_bt = b("." + c.Indent_bt);
            this.sIndent_bt = b("." + c.s_Indent_bt);
            this.Expand_Box = b("." + c.Expand_Box);
            this.Expand_bt = b("." + c.Expand_bt);
            this.miniMessageBox = b("." + c.miniMessageBox);
            this.miniChatIframe = b("#" + c.miniChatIframe);
            this.hasMessage = false;
            this.bindEvent();
            this.setDefault()
        }
        var f = null,
            c = null,
            m = {
                miniChatBox: "miniChat",
                CR_Indent_bt: "setMini",
                Indent_bt: "Indent_bt",
                s_Indent_bt: "miniBar .close",
                miniChatIframe: "miniChatMask",
                Expand_Box: "Expand_Box",
                Expand_bt: "Expand_bt",
                miniBar: "miniBar",
                shield_bt: "miniChat .del",
                miniChatTitle: "miniBar h4",
                switchBig_bt: "miniBar .zoom",
                miniMessageBox: "chatView",
                miniMessageList: "chatView ul",
                defaultNum: 0,
                defaultName: "",
                defaultTitle: "\u804a\u5929\u5ba4",
                checkJoin: function () {},
                hideChatBox: function () {},
                showChatBox: function () {}
            };
        j.prototype = {
                bindEvent: function () {
                    var a = this;
                    this.shield_bt.click(function (d) {
                        d.stopPropagation();
                        a.closeMiniPop(true)
                    });
                    this.miniBar.click(function (d) {
                        d.stopPropagation();
                        a.hideMini(d)
                    });
                    this.Expand_bt.click(function (d) {
                        d.stopPropagation();
                        a.hideMini(d)
                    });
                    this.messageList.click(function (d) {
                        d.stopPropagation();
                        a.hideMini(d)
                    });
                    this.switchBt.hover(function () {
                        b(this).addClass("zoomHover")
                    }, function () {
                        b(this).removeClass("zoomHover")
                    });
                    this.sIndent_bt.hover(function () {
                        b(this).addClass("closeHover")
                    }, function () {
                        b(this).removeClass("closeHover")
                    })
                },
                unBindEvent: function () {
                    this.switchBt.unbind("click");
                    this.shield_bt.unbind("click");
                    this.miniBar.unbind("click");
                    this.Expand_bt.unbind("click")
                },
                hideMini: function (a) {
                    if (a || window.event || 0) var d = k(a);
                    if (d && (d == this.Indent_bt.get(0) || d == this.sIndent_bt.get(0))) this.IndentMini();
                    else {
                        this.Expand_Box.hide();
                        this.Expand_bt.hide();
                        this.miniBox.hide();
                        a = c.checkJoin();
                        c.showChatBox();
                        a == 0 && b(".initialize a").click();
                        b.cookie("crstatus", 3, {
                            path: "/",
                            expires: 365
                        })
                    }
                },
                closeMiniPop: function (a) {
                    this.shield_bt.hide();
                    this.miniMessageBox.hide();
                    b("#miniChat .chatViewBg").hide();
                    a && this.resizeIframe({
                        w: this.miniBar.width() + 5,
                        h: 35,
                        r: 0
                    })
                },
                showMiniPop: function () {
                    this.shield_bt.show();
                    this.miniMessageBox.show()
                },
                resizeIframe: function (a) {
                    this.miniChatIframe.css({
                        width: a.w,
                        height: a.h,
                        right: a.r
                    })
                },
                IndentMini: function () {
                    c.hideChatBox();
                    this.miniBox.show();
                    this.miniBar.hide();
                    this.closeMiniPop();
                    this.Expand_bt.removeClass("expandbt_tp2");
                    this.Expand_Box.show();
                    this.Expand_bt.show();
                    var a = this.Expand_bt.width(),
                        d = this.Expand_bt.height(),
                        g = this.Expand_bt.css("right");
                    this.resizeIframe({
                            w: a,
                            h: d,
                            r: g
                        })
                },
                ExpandMini: function () {
                    this.Expand_Box.hide();
                    this.Expand_bt.hide();
                    this.Expand_bt.removeClass("expandbt_tp2");
                    this.miniBar.show();
                    this.showMiniPop();
                    this.resizeIframe({
                        w: this.miniBar.width() + 5,
                        h: 75,
                        r: 0
                    })
                },
                showMini: function () {
                    c.hideChatBox();
                    c.checkJoin() == 0 && this.setDefault();
                    this.miniBox.show();
                    this.showMiniPop();
                    this.Expand_bt.hide();
                    this.Expand_Box.hide();
                    this.miniBar.show();
                    this.resizeIframe({
                        w: this.miniBar.width() + 5,
                        h: 75,
                        r: 0
                    })
                },
                setDefault: function () {
                    var a = this;
                    a.miniTitle.html(c.defaultTitle);
                    this.messageList.html("");
                    var d = b("<li/>");
                    d.html("\u6b22\u8fce\u4f7f\u7528" + c.defaultTitle.replace("\u804a\u5929\u5ba4", "\u7f51\u9875\u804a\u5929\u5ba4"));
                    d.find("a:first").click(function () {
                        a.hideMini();
                        return false
                    });
                    d.appendTo(this.messageList)
                },
                setDefaultNum: function () {},
                newMessage: function (a) {
                    this.hasMessage = true;
                    var d = a.name,
                        g = a.message;
                    if (g.match('img src="http://simg.sinajs.cn/webchat/common/images/face/.*ghiz') !== null) g = "[\u8868\u60c5]";
                    var i = b("<li/>");
                    a.self === true ? i.html('<strong class="professor">' + d + "\uff1a</strong><span>" + g + "</span>") : i.html('<strong class="visitor">' + d + "\uff1a</strong><span>" + g + "</span>");
                    this.messageList.find("li").length != 1 && this.messageList.find("li:last").remove();
                    i.appendTo(this.messageList);
                    this.messageList.animate({
                            marginTop: "-25px"
                        }, 100, function () {
                            b(this).css({
                                marginTop: "0px"
                            }).find("li:first").appendTo(this)
                        });
                    this.Expand_bt.addClass("expandbt_tp2")
                },
                fn_bak: null
            };
        return {
                buildMiniChat: e,
                showMiniChat: h,
                newMiniMessage: o,
                setDefualtNum: p,
                destroyMini: q,
                callIndentMini: r,
                showChatWindow: n
            }
    }()
})(jQuery);
(function (c) {
    window.WUC = typeof window.WUC === "undefined" ? {} : window.WUC;
    WUC.global = {
        sLastSay: "",
        aFaceOrigin: [":)", "/tx", ":d", "/bz", ":-o", "/cy", "/qiao", ":(", "8o|", ":$", "/love", "/se", "/$", "/:>", "|-)", "/ms", "/lh", "/nm", "({)", "/qq", ":'(", "+o(", ":@", ":|", "/sj", "*-)", "/ty", "/tuu", "(H)", "/xu", "/?", "/yun", "/88", "/shuai", "/zhem", "/zc", ":p", ";)", "/fd", "/sd", "/bs", "/qiang", "/ws", "/shl", "/boy", "/girl", "/jc", "/gb", "(#)", "(u)", "(w)", "(s)", "/zq", "/cf", "/dg", "(L)", "/lq", "(F)", "/lw", "/mail", "(K)", "/pg", "/xg", "/kf", "/@@", "/xc", "/mao", "/gou", "/zt", "/bb", "/hl", "/%@", "(*)", "/xy", "/ys", "/ds", "/dh", "/dy", "/yy", "/dp", "/sz"],
        aFaceTransferred: [":\\)", "/tx", ":d", "/bz", ":-o", "/cy", "/qiao", ":\\(", "8o\\|", ":\\$", "/love", "/se", "/\\$", "/:&gt;", "\\|-\\)", "/ms", "/lh", "/nm", "\\(\\{\\)", "/qq", ":'\\(", "\\+o\\(", ":@", ":\\|", "/sj", "\\*-\\)", "/ty", "/tuu", "\\(H\\)", "/xu", "/\\?", "/yun", "/88", "/shuai", "/zhem", "/zc", ":p", ";\\)", "/fd", "/sd", "/bs", "/qiang", "/ws", "/shl", "/boy", "/girl", "/jc", "/gb", "\\(#\\)", "\\(u\\)", "\\(w\\)", "\\(s\\)", "/zq", "/cf", "/dg", "\\(L\\)", "/lq", "\\(F\\)", "/lw", "/mail", "\\(K\\)", "/pg", "/xg", "/kf", "/@@", "/xc", "/mao", "/gou", "/zt", "/bb", "/hl", "/%@", "\\(\\*\\)", "/xy", "/ys", "/ds", "/dh", "/dy", "/yy", "/dp", "/sz"],
        aFaceUrl: [29, 19, 8, 4, 5, 6, 7, 3, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 2, 20, 21, 22, 23, 24, 25, 26, 27, 28, 1, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 44, 43, 42, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81],
        clientTime: function () {
            function f(h) {
                if (h < 10) h = "0" + h;
                return h
            }
            now = new Date;
            return now = f(now.getHours()) + ":" + f(now.getMinutes()) + ":" + f(now.getSeconds())
        },
        htmlFilter: function (f) {
            return f = f.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/ /g, "&nbsp;").replace(/&/g, "&amp;")
        },
        tolerantFilter: function (f) {
            return f = f.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/ /g, "&nbsp;")
        },
        faceFilter: function (f) {
            for (var h = 0, d = WUC.global.aFaceOrigin.length; h < d; h++) f = f.replace(new RegExp(WUC.global.aFaceTransferred[h], "g"), '<img src="http://simg.sinajs.cn/webchat/common/images/face/' + WUC.global.aFaceUrl[h] + '.gif" class="ghiz">');
            return f
        },
        browser: function () {
            window.sBrowser = {};
            if (!sBrowser.platform) {
                var f = window.navigator.userAgent;
                sBrowser.platform = window.navigator.platform;
                sBrowser.firefox = f.indexOf("Firefox") > 0;
                sBrowser.opera = typeof window.opera === "object";
                sBrowser.ie = !sBrowser.opera && f.indexOf("MSIE") > 0;
                sBrowser.mozilla = window.navigator.product === "Gecko";
                sBrowser.netscape = window.navigator.vendor === "Netscape";
                sBrowser.safari = f.indexOf("Safari") > -1;
                if (sBrowser.firefox) var h = "firefox",
                    d = /Firefox(\s|\/)(\d+(\.\d+)?)/;
                else if (sBrowser.ie) {
                        h = "ie";
                        d = /MSIE( )(\d+(\.\d+)?)/
                    } else if (sBrowser.opera) {
                        h = "opera";
                        d = /Opera(\s|\/)(\d+(\.\d+)?)/
                    } else if (sBrowser.netscape) {
                        h = "netscape";
                        d = /Netscape(\s|\/)(\d+(\.\d+)?)/
                    } else if (sBrowser.safari) {
                        h = "safari";
                        d = /Version(\/)(\d+(\.\d+)?)/
                    } else if (sBrowser.mozilla) {
                        h = "mozilla";
                        d = /rv(\:)(\d+(\.\d+)?)/
                    }
                if ("undefined" !== typeof d && d.test(f)) sBrowser.version = parseFloat(RegExp.$2);
                this.name = h;
                this.version = sBrowser.version;
                return this
            }
        },
        insertWbr: function (f) {
            return f.replace(/([^a-z0-9][a-z0-9]{32}[^a-z0-9])|([a-z0-9]{16})/ig, function (h, d, a) {
                return d || a + "<wbr />"
            })
        },
        insertAt: function (f, h) {
            obj = c(f)[0];
            obj.focus();
            if (document.selection == null) {
                var d = obj.selectionEnd;
                obj.value = obj.value.substring(0, d) + h + obj.value.substring(d, obj.value.length)
            } else document.selection.createRange().text += h
        },
        customLen: function (f) {
            return f.match(/[^\r|\n| -~]/g) === null ? f.replace(/[\r|\n]/g, "").length : f.replace(/[\r|\n]/g, "").length + f.match(/[^\r|\n| -~]/g).length
        },
        beOriginal: function (f) {
            f = f.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/ /g, "&nbsp;").replace(/&/g, "&amp;");
            for (var h = 0, d = WUC.global.aFaceOrigin.length; h < d; h++) {
                var a = new RegExp(WUC.global.aFaceTransferred[h], "g");
                f = WUC.global.browser().name === "ie" ? f.replace(a, '<IMG class=ghiz src="http://simg.sinajs.cn/webchat/common/images/face/' + WUC.global.aFaceUrl[h] + '.gif">') : f.replace(a, '<img src="http://simg.sinajs.cn/webchat/common/images/face/' + WUC.global.aFaceUrl[h] + '.gif" class="ghiz">')
            }
            return f
        },
        getItemByAttr: function (f, h, d) {
            if (typeof f === "undefined" || !(h instanceof Array)) return null;
            d = d || "id";
            try {
                for (var a = 0, b = h.length; a < b; a++) if (h[a][d] === f) return {
                    obj: h[a],
                    index: a
                }
            } catch (e) {
                return null
            }
        },
        delItemByAttr: function (f, h, d) {
            if (typeof f === "undefined" || !(h instanceof Array)) return null;
            d = d || "id";
            if (f = this.getItemByAttr(f, h, d)) {
                h.splice(f.index, 1);
                return f.obj
            } else return null
        },
        getEvent: function () {
            var f = window.event;
            if (!f) try {
                for (var h = arguments.callee; !h || !h.arguments[0] || !((h.arguments[0].originalEvent || h.arguments[0]) instanceof Event);) h = h.caller;
                f = h.arguments[0].originalEvent || h.arguments[0]
            } catch (d) {
                f = null
            }
            return f
        }
    };
    WUC.ChatRoom_UI = function (f) {
        window.webUCBaseRoomClient = f;
        var h = parseInt(c.cookie("crstatus") || 2, 10),
            d = {};
        d.Base = {
                chatRoomMiniSize: {
                    width: 450,
                    height: 300
                },
                webChatSpaceId: "finance_webchat_id",
                fakeChatBox: null,
                curRoom: null,
                rooms: [],
                me: null,
                roomArg: {
                    maxNum: 0,
                    niceWidth: 120,
                    minWidth: 90,
                    lastNum: 0,
                    roomFix: 2,
                    factor: []
                },
                ui: {
                    containerEl: null,
                    roomCtn: null,
                    moreRoomEl: null,
                    roomListCtn: null,
                    memberListCtn: null,
                    memberListTitle: null,
                    signTitle: null,
                    signList: null,
                    noSignTitle: null,
                    noSignList: null,
                    dropMenu: null,
                    ctrlMenu: [],
                    searchCtn: null,
                    headEl: null
                },
                loadDefaultMsg: false,
                domToObj: function (a, b, e) {
                    if (typeof a === "undefined" || !(b instanceof Array)) return null;
                    try {
                        for (var g = 0, k = b.length; g < k; g++) if (eval("objList[" + g + "]." + e)[0] === a) return b[g]
                    } catch (l) {
                        return null
                    }
                },
                beforeInitDOM: function () {
                    var a = miniDisplay = chatRoomDisplay = expand = expandDel = chatview = "";
                    switch (h) {
                    case 1:
                        a = 'class="CRstock"';
                        chatview = 'style="display:none;"';
                        expand = expandDel = 'style="display:block;"';
                        break;
                    case 2:
                        a = 'class="CRstock"';
                        break;
                    case 3:
                        miniDisplay = 'style="display:none"';
                        a = 'class="CRstock"';
                        break;
                    case 4:
                        miniDisplay = 'style="display:none"';
                        break
                    }
                    var b = this.titleInfo;
                    b = ['<div id="miniChat" ' + miniDisplay + ">", '<iframe frameborder="0" id="miniChatMask"></iframe>', '<div class="chatViewBg" ' + chatview + "></div>", '<div class="miniBar" ' + chatview + ">", '<a class="Indent_bt" ' + (b.toMiniBar ? "title=" + b.toMiniBar : "") + "></a>", '<div title="Xtalk" class="xTalkLogo"></div>', '<span class="close" ' + (b.midToMin ? "title=" + b.midToMin : "") + "></span>", '<span class="zoom" ' + (b.midToWin ? "title=" + b.midToWin : "") + "></span>", '<div class="pp_ico"></div><h4></h4></div>', '<div class="chatView" ' + chatview + "><ul></ul></div>", '<a class="del" href="javascript:void(0)" ' + chatview + "></a>", '<div class="Expand_Box" ' + expand + "><a " + (b.toMidBar ? "title=" + b.toMidBar : "") + ' class="Expand_bt expandbt_tp1"' + expandDel + "></a></div>", "</div>"].join("");
                    var e = c("CRstock");
                    if (e[0]) {
                        h < 4 && e.addClass("CRstock");
                        e.append(b)
                    } else {
                        e = c('<div id="ucChatDiv"></div>');
                        e.append('<div id="CRstock" ' + a + ">" + b + "</div>");
                        h == 4 ? c("#" + d.Base.webChatSpaceId).append(e) : c("body").append(e)
                    }
                    c("#popUcMessage");
                    b = b = null;
                    c(".Indent_bt").click(function () {
                        c(".chatViewBg").hide();
                        c(".miniBar").hide();
                        c(".chatView").hide();
                        c(".Expand_Box").show();
                        c(".Expand_bt").show();
                        c.cookie("crstatus", 1, {
                            path: "/",
                            expires: 365
                        })
                    })
                },
                beforeInit: function () {
                    this.beforeInitDOM()
                },
                initDOM: function (a) {
                    miniDisplay = chatRoomDisplay = "";
                    switch (h) {
                    case 1:
                        chatRoomDisplay = a ? 'style="display:none"' : "";
                        break;
                    case 2:
                        chatRoomDisplay = a ? 'style="display:none"' : "";
                        break;
                    case 3:
                        miniDisplay = 'style="display:none"';
                        break;
                    case 4:
                        miniDisplay = 'style="display:none"';
                        break
                    }
                    a = this.titleInfo;
                    a = ['<div id="ucChatRoom" ' + chatRoomDisplay + ">", '<div class="dropMenu"><a class="chatWith" href="javascript:void(0);">\u4e0eTA\u804a\u5929</a><a class="stopChat" href="javascript:void(0);">\u5c4f\u853dTA</a><a class="relieveStop" href="javascript:void(0);">\u89e3\u9664\u5c4f\u853d</a></div><div id="CRheader"><a href="http://www.xtalk.cn" title="Xtalk" target="_blank" class="xTalkLogo"></a>', '<span class="setClose" ' + (a.winCloseButton ? "title=" + a.winCloseButton : "") + "></span>", '<span class="setMini" ' + (a.winMiniButton ? "title=" + a.winMiniButton : "") + "></span>", '<div class="afterLogin">', '<span>\u6b22\u8fce\u6765\u5230\u804a\u5929\u5ba4</span><span id="myNickname" class="available"></span><a id="logout" href="/member/logout.php" style="display:none" ' + (a.logoutTitle ? "title=" + a.logoutTitle : "") + ">\u9000\u51fa</a>", "</div>", '<div class="beforeLogin"><span ' + (a.resetName ? "title=" + a.resetName : "") + '><input maxlength="20" type="text" /><u></u></span><a href="/member/login.php" class="login" ' + (a.loginTitle ? "title=" + a.loginTitle : "") + ' style="display:none;">\u767b\u5f55</a><a href="/member/register.php" target="_blank" class="signup" ' + (a.regTitle ? "title=" + a.regTitle : "") + ' style="display:none;">\u6ce8\u518c</a>&nbsp;&nbsp;</div>', '</div><div id="CRcontainer"><div class="roomTab">', '<a href="javascript:void(0);" class="moreRoom" ' + (a.moreRoom ? "title=" + a.moreRoom : "") + "></a>", '<div class="moreRoomDiv"></div></div><div class="chat"><div class="groupMember"><div class="infoLinkWrapper"><a href="http://www.xtalk.cn/" target="_blank">Powered By XTalk</a></div>', '<div class="groupTitle"><a hidefocus="ture" ' + (a.searchButton ? "title=" + a.searchButton : "") + "></a><span>0</span>\u4eba\u53c2\u4e0e\u804a\u5929</div>", '<div class="memberList">', '<h3 class="dropDown"' + (f.siteApi ? "" : ' style="display:none"') + ">" + f.logGroupName + "<span>(0)</span></h3>", "<ul" + (f.siteApi ? "" : ' style="display:none"') + "></ul>", '<h3 class="dropDown">\u6e38\u5ba2<span>(0)</span></h3><ul></ul></div></div>', '<div class="hideGroupMember" ' + (a.hideList ? "title=" + a.hideList : "") + "></div>", '<div class="showGroupMember" ' + (a.showList ? "title=" + a.showList : "") + "></div>", '<div class="searchMemberList"><div class="searchInput"><p><a href="javascript:void(0);"></a><input type="text" maxlength="10" /></p></div><div class="searchResult"><h3 class="dropDown">\u53c2\u4e0e\u8005<span>(0)</span></h3><ul></ul><h3 class="dropDown">\u6e38\u5ba2<span>(0)</span></h3><ul></ul></div></div><div class="chatArea"><div class="chatBody"><ul></ul></div><form><div class="chatInput"><div><textarea>\u8bf7\u5728\u8fd9\u91cc\u8f93\u5165\u5185\u5bb9\u3002</textarea></div></div></form><div class="buttonArea">', "<span " + (a.sendButton ? "title=" + a.sendButton : "") + ">\u53d1\u9001</span>", '<a href="javascript:void(0);" ' + (a.emotionButton ? "title=" + a.emotionButton : "") + "></a>", '</div></div></div></div><div class="initialize"><p>\u7f51\u53cb\u70ed\u8bc4\uff0c\u5b9e\u65f6\u8bc4\u8bba<br><a href="javascript:void(0);">\u7acb\u5373\u53c2\u4e0e\u804a\u5929</a></p></div><div id="CRloading"><p>\u6b63\u5728\u52a0\u8f7d,\u8bf7\u7a0d\u5019...</p></div><div class="emotDiv"><table border="0" cellpadding="4" cellspacing="1"><tbody><tr><td title="\u5fae\u7b11" abbr=":)"></td><td title="\u7a83\u7b11" abbr="/tx"></td><td title="\u5927\u7b11" abbr=":d"></td><td title="\u95ed\u5634" abbr="/bz"></td><td title="\u5403\u60ca" abbr=":-o"></td><td title="\u5472\u7259" abbr="/cy"></td><td title="\u6253\u4f60" abbr="/qiao"></td><td title="\u60b2\u4f24" abbr=":("></td><td title="\u6124\u6012" abbr="8o|"></td><td title="\u5bb3\u7f9e" abbr=":$"></td><td title="\u597d\u7231\u4f60" abbr="/love"></td><td title="\u597d\u8272" abbr="/se"></td><td title="\u89c1\u94b1\u773c\u5f00" abbr="/$"></td><td title="\u53ef\u7231" abbr="/:>"></td></tr><tr><td title="\u56f0" abbr="|-)"></td><td title="\u61d2\u5f97\u7406\u4f60" abbr="/ms"></td><td title="\u6d41\u6c57" abbr="/lh"></td><td title="\u6012\u9a82" abbr="/nm"></td><td title="\u62b1\u62b1" abbr="({)"></td><td title="\u4eb2\u4eb2" abbr="/qq"></td><td title="\u4f24\u5fc3\u6b7b\u4e86" abbr=":\'("></td><td title="\u751f\u75c5" abbr="+o("></td><td title="\u751f\u6c14" abbr=":@"></td><td title="\u5931\u671b" abbr=":|"></td><td title="\u7761\u89c9" abbr="/sj"></td><td title="\u601d\u8003" abbr="*-)"></td><td title="\u8ba8\u538c" abbr="/ty"></td><td title="\u5410" abbr="/tuu"></td></tr><tr><td title="\u6446\u9177" abbr="(H)"></td><td title="\u5618" abbr="/xu"></td><td title="\u7591\u95ee" abbr="/?"></td><td title="\u6655" abbr="/yun"></td><td title="\u518d\u89c1" abbr="/88"></td><td title="\u6574\u86ca" abbr="/shuai"></td><td title="\u6293\u72c2" abbr="/zhem"></td><td title="\u5634\u998b" abbr="/zc"></td><td title="\u505a\u9b3c\u8138" abbr=":p"></td><td title="\u7728\u773c" abbr=";)"></td><td title="\u975e\u5178" abbr="/fd"></td><td title="\u4e66\u5446\u5b50" abbr="/sd"></td><td title="\u9119\u89c6" abbr="/bs"></td><td title="\u8d5e" abbr="/qiang"></td></tr><tr><td title="\u63e1\u624b" abbr="/ws"></td><td title="\u80dc\u5229" abbr="/shl"></td><td title="\u7537\u5b69" abbr="/boy"></td><td title="\u5973\u5b69" abbr="/girl"></td><td title="\u8b66\u5bdf" abbr="/jc"></td><td title="\u5e72\u676f" abbr="/gb"></td><td title="\u592a\u9633" abbr="(#)"></td><td title="\u5fc3\u788e" abbr="(u)"></td><td title="\u852b\u513f\u73ab\u7470\u82b1" abbr="(w)"></td><td title="\u6708\u4eae" abbr="(s)"></td><td title="\u8db3\u7403" abbr="/zq"></td><td title="\u5403\u996d" abbr="/cf"></td><td title="\u86cb\u7cd5" abbr="/dg"></td><td title="\u7ea2\u5fc3" abbr="(L)"></td></tr><tr><td title="\u7bee\u7403" abbr="/lq"></td><td title="\u73ab\u7470\u82b1" abbr="(F)"></td><td title="\u793c\u7269" abbr="/lw"></td><td title="\u90ae\u4ef6" abbr="/mail"></td><td title="\u70ed\u543b" abbr="(K)"></td><td title="\u82f9\u679c" abbr="/pg"></td><td title="\u897f\u74dc" abbr="/xg"></td><td title="\u5496\u5561" abbr="/kf"></td><td title="\u5c0f\u7cbe\u7075" abbr="/@@"></td><td title="\u5c0f\u4e11" abbr="/xc"></td><td title="\u732b" abbr="/mao"></td><td title="\u72d7" abbr="/gou"></td><td title="\u732a\u5934" abbr="/zt"></td><td title="\u4fbf\u4fbf" abbr="/bb"></td></tr><tr><td title="\u6d3b\u529b\u56db\u5c04" abbr="/hl"></td><td title="\u9ab7\u9ac5" abbr="/%@"></td><td title="\u5c0f\u661f\u661f" abbr="(*)"></td><td title="\u4e0b\u96e8\u4e86" abbr="/xy"></td><td title="\u4f1e" abbr="/ys"></td><td title="\u7535\u89c6" abbr="/ds"></td><td title="\u7535\u8bdd" abbr="/dh"></td><td title="\u7535\u5f71" abbr="/dy"></td><td title="\u97f3\u4e50" abbr="/yy"></td><td title="\u706f\u6ce1" abbr="/dp"></td><td title="\u65f6\u949f" abbr="/sz"></td><td></td><td></td><td></td></tr></tbody></table></div><div id="loginWebuc"><h3><a class="del" title="\u5173\u95ed\u767b\u5f55\u7a97\u53e3" href="#"></a>\u767b\u5f55\u65b0\u6d6a\u901a\u884c\u8bc1</h3><div class="login"><div><label for="username">\u767b\u5f55\u540d\uff1a</label><input id="username" /><span rel="username">\u8bf7\u8f93\u5165\u7528\u6237\u540d</span><label for="password">\u5bc6&nbsp;&nbsp;\u7801\uff1a</label><input id="password" type="password" /><span rel="password">\u8bf7\u8f93\u5165\u5bc6\u7801</span></div><p><button>\u767b \u5f55</button><a target="_blank" href="http://im.sina.com.cn/reg.php">\u7acb\u5373\u6ce8\u518c</a><a target="_blank" href="https://login.sina.com.cn/getpass.html">\u627e\u56de\u5bc6\u7801</a></p></div><div class="CRloginLoading"><p>\u767b\u5f55\u4e2d\u3002</p><img src="http://simg.sinajs.cn/webchat/common/css/bgimages/loading.gif" /></div></div><div id="roomSwitch"><span></span><div>\u6b63\u5728\u5207\u6362\u623f\u95f4,\u8bf7\u7a0d\u5019...</div><p>\u6b64\u623f\u95f4\u5df2\u6ee1\u5458\uff0c\u8bf7\u7a0d\u5019\u91cd\u8bd5\u3002</p></div><div id="LostService" class="" ><span></span><div>\u91cd\u65b0\u8fde\u63a5\u670d\u52a1\u5668,\u8bf7\u7a0d\u5019...</div><p><em>\u670d\u52a1\u5668\u8fde\u63a5\u65ad\u5f00\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u72b6\u51b5\u3002</em><em><a href="#">\u7acb\u5373\u8fde\u63a5</a><u>10</u>  \u79d2\u540e\u81ea\u52a8\u8fde\u63a5\u3002</em></p></div><iframe id="hideSelect"></iframe><div id="shadeDiv"></div><div id="transparentDiv"></div><div class="errorTips"></div></div>'].join("");
                    var b = c("#CRstock");
                    if (b[0]) {
                        h < 4 && b.addClass("CRstock");
                        b.append(a)
                    }
                },
                init: function (a) {
                    this.initDOM(a);
                    var b = this,
                        e = function () {
                            var g = WUC.global.getEvent();
                            if (g) {
                                for (g = g.toElement || g.relatedTarget; g != document.body;) try {
                                    if (g == b.ui.roomListCtn[0] || g == b.ui.moreRoomEl[0]) return;
                                    g = g.parentNode
                                } catch (k) {
                                    g = document.body
                                }
                                e.timeout = setTimeout(function () {
                                    b.ui.roomListCtn.hide()
                                }, 1E3)
                            }
                        };
                    a = function () {
                            var g = WUC.global.getEvent();
                            if (g) {
                                g = g.srcElement || g.target;
                                try {
                                    if (!b.curRoom) return;
                                    var k = b.domToObj(g.parentNode, b.curRoom.memberList, "ui.el");
                                    if (!k) return;
                                    if (!b.me || k.username != b.me.username) k.showCtrlMenu()
                                } catch (l) {}
                                return false
                            }
                        };
                    this.containerEl = c("#ucChatRoom");
                    if (this.containerEl[0]) {
                            this.ui.roomCtn = c("#ucChatRoom .roomTab");
                            this.ui.roomListCtn = c("#ucChatRoom .moreRoomDiv").hide().click(function () {
                                b.ui.roomListCtn && b.ui.roomListCtn.hide();
                                return false
                            }).mouseenter(function () {
                                if (e.timeout) {
                                    clearTimeout(e.timeout);
                                    delete e.timeout
                                }
                            }).mouseout(e);
                            this.ui.moreRoomEl = c("#ucChatRoom .moreRoom").hide().click(function () {
                                b.ui.roomListCtn && b.ui.roomListCtn.show();
                                return false
                            }).mouseout(e);
                            this.ui.memberListCtn = c("#ucChatRoom .memberList");
                            this.ui.memberListTitle = c("#ucChatRoom .groupTitle");
                            this.ui.dropMenu = c("#ucChatRoom .dropMenu").hide();
                            this.ui.ctrlMenu[0] = c("#ucChatRoom .chatWith").click(function (g) {
                                b.curRoom && b.curRoom.curMember && b.curRoom.curMember.chatWithMe();
                                g.preventDefault()
                            });
                            this.ui.ctrlMenu[1] = c("#ucChatRoom .stopChat").click(function (g) {
                                b.curRoom && b.curRoom.curMember && b.curRoom.curMember.block();
                                g.preventDefault()
                            });
                            this.ui.ctrlMenu[2] = c("#ucChatRoom .relieveStop").click(function (g) {
                                b.curRoom && b.curRoom.curMember && b.curRoom.curMember.block();
                                g.preventDefault()
                            });
                            this.ui.signList = this.ui.memberListCtn.find("ul").eq(0).click(a);
                            this.ui.noSignList = this.ui.memberListCtn.find("ul").eq(1).click(a);
                            this.ui.signTitle = this.ui.memberListCtn.find("h3").eq(0).click(function (g) {
                                this.className = this.className == "dropDown" ? "dropRight" : "dropDown";
                                this.className == "dropDown" ? b.ui.signList.show() : b.ui.signList.hide();
                                g.preventDefault()
                            });
                            this.ui.noSignTitle = this.ui.memberListCtn.find("h3").eq(1).click(function (g) {
                                this.className = this.className == "dropDown" ? "dropRight" : "dropDown";
                                this.className == "dropDown" ? b.ui.noSignList.show() : b.ui.noSignList.hide();
                                g.preventDefault()
                            });
                            this.resize()
                        }
                },
                getSelf: function (a) {
                    if (a && a.username) {
                        this.me = a;
                        d.Addon.initName(a.nick || a.username);
                        if (this.curRoom) {
                            if (a = WUC.global.getItemByAttr(a.username, this.curRoom.memberList, "username")) this.me = a.obj;
                            d.Addon.checkShow()
                        }
                    }
                },
                setCurRoom: function (a) {
                    a = Math.abs(a);
                    var b = WUC.global.getItemByAttr(a, this.rooms);
                    if (b) this.atRoom(b.obj.id);
                    else this.curRoom = new d.ChatRoom(a)
                },
                resetAllRooms: function () {
                    if (!(!this.ui.roomCtn && this.rooms.length === 0)) {
                        var a = this.roomArg,
                            b = this.ui.roomCtn[0].offsetWidth,
                            e = a.niceWidth,
                            g = 0;
                        a.maxNum = parseInt(b / e, 10);
                        b -= this.ui.moreRoomEl[0].offsetWidth;
                        if (e * a.maxNum > b) {
                                e -= parseInt((e * a.maxNum - b) / a.maxNum, 10);
                                g = e * a.maxNum - b;
                                for (b = 0; b < this.rooms.length; b++) {
                                    var k = e - a.roomFix - (g-- > 0 ? 1 : 0);
                                    this.rooms[b].inList || this.rooms[b].boxEl.width(k)
                                }
                            } else {
                                e += parseInt(b % e / a.maxNum, 10);
                                g = b - e * a.maxNum;
                                for (b = 0; b < this.rooms.length; b++) {
                                    k = e - a.roomFix + (g-- > 0 ? 1 : 0);
                                    this.rooms[b].inList || this.rooms[b].boxEl.width(k)
                                }
                            }
                        this.rooms.length > a.maxNum ? this.ui.moreRoomEl.show() : this.ui.moreRoomEl.hide()
                    }
                },
                resize: function () {
                    this.containerEl.width(parseInt(this.containerEl.parent().width()));
                    this.ui.roomCtn && this.resetAllRooms()
                },
                changeRoom: function (a, b) {
                    if (b) {
                        this.dataReset();
                        f.getDefaultMsg()
                    } else d.Message.roomSwitch("failed")
                },
                atRoom: function (a) {
                    if (!(typeof a === "undefined" || isNaN(a))) {
                        var b = WUC.global.getItemByAttr(a, this.rooms);
                        if (b) {
                            b = b.obj;
                            this.roomArg.factor.push(WUC.global.delItemByAttr(b.id, this.roomArg.factor) || b);
                            if (b.inList) {
                                this.ui.roomListCtn.append(this.roomArg.factor[0].boxEl);
                                this.ui.roomCtn.append(b.boxEl);
                                b.boxEl.width(this.roomArg.factor[0].boxEl.css("width"));
                                this.roomArg.factor[0].boxEl.css("width", "");
                                b.inList = false;
                                this.roomArg.factor[0].inList = true;
                                for (var e = 0, g = this.rooms.length; e < g; e++) if (this.rooms[e].inList && this.rooms[e].id > this.roomArg.factor[0].id) {
                                    this.roomArg.factor[0].boxEl.insertBefore(this.rooms[e].boxEl);
                                    break
                                }
                                this.roomArg.factor.shift();
                                this.ui.roomListCtn.hide()
                            }
                            try {
                                this.curRoom && this.curRoom.boxEl.removeClass("on")
                            } catch (k) {}
                            this.curRoom = b;
                            this.curRoom.boxEl.addClass("on");
                            d.Message.roomSwitch("success");
                            this.loadDefaultMsg = f.getDefaultMsg();
                            var l = setInterval(function () {
                                if (d.Base.loadDefaultMsg) {
                                    d.Message.warmTips("join", a);
                                    clearInterval(l);
                                    l = null
                                }
                            }, 500)
                        }
                    }
                },
                addRoom: function (a) {
                    a instanceof Array || (a = [a]);
                    for (var b = 0, e = a.length; b < e; b++) if (!(a[b].c_id >= 0)) {
                        var g = Math.abs(a[b].c_id);
                        if (a[b].c_ucount == 0) this.removeRoom(g);
                        else if (!(isNaN(g) || WUC.global.getItemByAttr(g, this.rooms))) {
                            var k, l = false;
                            k = !this.curRoom || this.curRoom.id != g ? new d.ChatRoom(g) : this.curRoom;
                            this.rooms.push(k);
                            if (this.rooms.length > this.roomArg.maxNum) {
                                this.ui.moreRoomEl.show();
                                l = true
                            }
                            k.init(g, l);
                            l || this.roomArg.factor.unshift(k);
                            k.boxEl.click(function (n, m) {
                                return function () {
                                    if (!n.curRoom || m === n.curRoom.id) return false;
                                    d.Message.roomSwitch("start");
                                    window.setTimeout(function () {
                                        f.change(-parseInt(m, 10))
                                    }, 100);
                                    return false
                                }
                            }(this, k.id));
                            this.resetAllRooms()
                        }
                    }
                    g = k = null
                },
                removeRoom: function (a) {
                    a = Math.abs(a);
                    if (!isNaN(a)) {
                        var b = WUC.global.getItemByAttr(a, this.rooms);
                        if (b) {
                            b.obj.boxEl.remove();
                            this.rooms.splice(b.index, 1)
                        }
                        if (factorObj = WUC.global.getItemByAttr(a, this.roomArg.factor)) {
                            this.roomArg.factor.splice(factorObj.index, 1);
                            a = 0;
                            for (b = this.rooms.length; a < b; a++) if (this.rooms[a].inList) {
                                this.ui.roomCtn.append(this.rooms[a].boxEl);
                                this.rooms[a].inList = false;
                                this.roomArg.factor.unshift(this.rooms[a]);
                                break
                            }
                        }
                        if (this.rooms.length <= this.roomArg.factor.length) {
                            this.ui.moreRoomEl.hide();
                            this.resetAllRooms()
                        }
                    }
                },
                checkBlock: function (a) {
                    if (a = WUC.global.getItemByAttr(a, this.curRoom.blockList, "username")) return a.obj.isBlock;
                    return false
                },
                dataReset: function () {
                    if (this.curRoom) {
                        this.curRoom.boxEl.removeClass("on");
                        this.curRoom.memberList = [];
                        this.curRoom.chatList = [];
                        this.curRoom.blockList = [];
                        this.curRoom.curMember = null;
                        this.curRoom.removeMember()
                    }
                },
                dragEvt: function () {
                    var a = {
                        o: c("#ucChatRoom"),
                        x: 0,
                        y: 0,
                        tx: 0,
                        ty: 0,
                        l: 0,
                        t: 0,
                        w: 0,
                        h: 0,
                        d: false,
                        c: null
                    };
                    c(document).bind("mousedown", function (b) {
                        a.d = c(b.target).is("#CRheader") || c(b.target.parentNode).is("#CRheader") && b.target.tagName.toUpperCase() == "DIV";
                        if (a.d) {
                            var e = a.o.position().top;
                            a.x = b.pageX - (1 * a.o.css("left").replace(/px/gi, "") || 0);
                            a.y = b.pageY - e;
                            a.l = b.pageX - a.o.offset().left;
                            a.t = b.pageY - a.o.offset().top;
                            a.w = document.documentElement.clientWidth;
                            a.h = document.documentElement.clientHeight;
                            c.browser.msie ? a.o.get(0).setCapture() : b.preventDefault()
                        }
                    }).bind("mousemove", function (b) {
                        if (a.d) {
                            a.tx = b.pageX - a.x;
                            a.ty = b.pageY - a.y;
                            if (b.clientY - a.t < 0) a.ty = b.pageY - b.clientY - a.y + a.t;
                            else if (b.clientY + a.o.height() - a.t > a.h) a.ty = b.pageY - b.clientY - a.y + (a.h - a.o.height()) + a.t;
                            if (b.clientX - a.l < 0) a.tx = b.pageX - b.clientX - a.x + a.l;
                            else if (b.clientX + a.o.width() - a.l > a.w) a.tx = b.pageX - b.clientX - a.x + (a.w - a.o.width()) + a.l;
                            if (!a.c) a.c = setInterval(function () {
                                a.o.css({
                                    left: a.tx,
                                    top: a.ty
                                })
                            }, 10)
                        }
                    }).bind("mouseup", function () {
                        if (a.d) {
                            a.c && clearInterval(a.c);
                            a.d = false;
                            a.c = null;
                            c.browser.msie && a.o.get(0).releaseCapture()
                        }
                    });
                    c("#CRheader").bind("mousemove", function (b) {
                        c(this).css("cursor", b.target.tagName.toUpperCase() != "DIV" ? "default" : "move")
                    });
                    c(window).bind("resize", this.resetLayer)
                },
                resetPopPosition: function () {},
                resetLayer: function () {
                    c("#ucChatRoom").css({
                        top: "0px",
                        left: "0px"
                    })
                },
                reload: function () {
                    d.Addon.checkShow()
                },
                clearFakeChatBox: function () {
                    if (d.Base.fakeChatBox) {
                        d.Base.fakeChatBox.unbind();
                        d.Base.fakeChatBox.remove();
                        d.Base.fakeChatBox = null
                    }
                },
                titleInfo: {
                    winMiniButton: "\u6700\u5c0f\u5316",
                    emotionButton: "\u9009\u62e9\u8868\u60c5",
                    sendButton: "\u70b9\u51fb\u53d1\u9001\u6d88\u606f ",
                    searchButton: "\u641c\u7d22\u804a\u5929\u53c2\u4e0e\u8005",
                    toMiniBar: "\u6536\u8d77\u804a\u5929\u5ba4",
                    toMidBar: "\u5c55\u5f00\u804a\u5929\u5ba4",
                    midToWin: "\u6700\u5927\u5316",
                    resetName: "\u4fee\u6539\u6e38\u5ba2\u540d\u79f0",
                    moreRoom: "\u66f4\u591a\u623f\u95f4",
                    hideList: "\u9690\u85cf\u804a\u5929\u53c2\u4e0e\u8005\u5217\u8868",
                    showList: "\u663e\u793a\u804a\u5929\u53c2\u4e0e\u8005\u5217\u8868",
                    winCloseButton: "\u5173\u95ed",
                    midToMin: "\u5173\u95ed"
                }
            };
        d.AlertMessage = {
                MsgQueue: [
                    [],
                    []
                ],
                FixQueue: [
                    [],
                    []
                ],
                pElm: c("#ucChatRoom .chatBody .alerMsgCon"),
                show: function (a, b) {
                    if (!(this.hasBeenShow(this.MsgQueue, a) || this.hasBeenShow(this.FixQueue, a))) {
                        if (this.pElm.length == 0) {
                            this.pElm = c('<div class="alerMsgCon"></div>');
                            this.pElm.appendTo(c("#ucChatRoom .chatBody"));
                            this.pElm.css({
                                left: -4,
                                top: -c("#ucChatRoom .chatBody ul").height() - 10
                            })
                        }
                        this.pElm.show();
                        var e = c('<div class="alertMessage"><p><a href="javascript:void(0);" class="del"/><span class="icon"/>' + a + "</p></div>");
                        e.appendTo(this.pElm);
                        if (b) {
                            this.FixQueue[0].push(e);
                            this.FixQueue[1].push(a)
                        } else {
                            this.MsgQueue[0].push(e);
                            this.MsgQueue[1].push(a)
                        }
                        var g = this;
                        e.find(".del").bind("click", function () {
                            g.clear(e)
                        });
                        b || setTimeout(function () {
                            g.hasBeenShow(g.MsgQueue, e) && g.hide(e)
                        }, 1500)
                    }
                },
                hide: function (a) {
                    var b = this;
                    a.fadeOut("slow", function () {
                        b.clear(a)
                    })
                },
                clear: function (a) {
                    if (this.hasBeenShow(this.MsgQueue, a)) {
                        this.MsgQueue[0].shift();
                        this.MsgQueue[1].shift()
                    } else if (this.hasBeenShow(this.FixQueue, a)) {
                        this.FixQueue[0].shift();
                        this.FixQueue[1].shift()
                    }
                    a.find(".del").unbind("click");
                    a.remove();
                    this.MsgQueue[0].length == 0 && this.FixQueue[0].length == 0 && this.pElm.hide()
                },
                hasBeenShow: function (a, b) {
                    for (var e = 0; e < a[0].length; e++) if (a[0][e] == b || a[1][e] == b) return true;
                    return false
                },
                removeAll: function () {
                    for (var a = [this.MsgQueue[0].concat(this.FixQueue[0]), this.MsgQueue[1].concat(this.FixQueue[1])], b = 0; b < a[0].length; b++) {
                        var e = a[0][b];
                        e.unbind("click");
                        e.remove()
                    }
                    this.pElm.length != 0 && this.pElm.hide();
                    this.MsgQueue = [
                        [],
                        []
                    ];
                    this.FixQueue = [
                        [],
                        []
                    ]
                },
                linkLose: function () {
                    var a = c("#ucChatRoom .linkLose");
                    if (a.length == 0) {
                        a = c('<div class="linkLose"><span/><p><em><a href="javascript:void(0);" class="del"/>\u4e0e\u670d\u52a1\u5668\u8fde\u63a5\u5f02\u5e38\uff0c\u53ef\u80fd\u65e0\u6cd5\u7ee7\u7eed\u804a\u5929\u3002</em><em><a href="javascript:void(0);" target="_self" onclick="javascript:location.reload();" title="\u5237\u65b0\u540e\u9875\u9762\u6570\u636e\u9700\u8981\u91cd\u65b0\u52a0\u8f7d">\u5237\u65b0\u9875\u9762</a><u></u></em></p></div>').appendTo(c("#ucChatRoom"));
                        a.find(".del").bind("click", function () {
                            a.hide();
                            c("#transparentDiv").hide()
                        })
                    }
                    a.show();
                    c("#transparentDiv").show()
                }
            };
        d.Message = {
                sPrevious: "",
                roomSwitch: function (a) {
                    switch (a) {
                    case "start":
                        c("#transparentDiv").show();
                        c("#roomSwitch").show();
                        break;
                    case "success":
                        c(".chatBody ul").html("");
                        c("#transparentDiv").hide();
                        c("#roomSwitch").hide();
                        break;
                    case "failed":
                        c("#roomSwitch").addClass("failed");
                        setTimeout(function () {
                            c("#roomSwitch").removeClass("failed").hide();
                            c("#transparentDiv").hide()
                        }, 2500);
                        break
                    }
                },
                msgTrim: function (a) {
                    return a = a.replace(/^\s+|\s+$/g, "")
                },
                showError: function (a) {
                    switch (a) {
                    case "sensitive":
                        break;
                    case "vacant":
                        d.AlertMessage.show("\u8bf7\u8f93\u5165\u5185\u5bb9\u540e\u53d1\u9001\u3002");
                        break;
                    case "repetitive":
                        d.AlertMessage.show("\u8bf7\u4e0d\u8981\u91cd\u590d\u53d1\u9001\u76f8\u540c\u4fe1\u606f\u3002");
                        break;
                    case "overbrim":
                        d.AlertMessage.show("\u6bcf\u6b21\u53d1\u8a00\u8bf7\u52ff\u8d85\u8fc7140\u4e2a\u5b57\u7b26\u621670\u4e2a\u6c49\u5b57\u3002");
                        break;
                    case "excessive":
                        d.AlertMessage.show("\u4f60\u53d1\u9001\u6d88\u606f\u7684\u901f\u5ea6\u8fc7\u5feb\uff0c\u8bf7\u95f4\u96942\u79d2\u540e\u53d1\u9001\u4e0b\u4e00\u6761\u3002");
                        break;
                    case "illegal":
                        d.AlertMessage.show("\u4f60\u6240\u4fee\u6539\u7684\u540d\u5b57\u4e2d\u5305\u542b\u975e\u6cd5\u5b57\u7b26\u3002");
                        break;
                    case "username":
                        c("#loginWebuc span[rel='username']").css("visibility", "visible");
                        break;
                    case "password":
                        c("#loginWebuc span[rel='password']").css("visibility", "visible");
                        break;
                    case "loseLink":
                        d.AlertMessage.linkLose();
                        break
                    }
                },
                say: function () {
                    if (f.isLoseLink) d.Message.showError("loseLink");
                    else {
                        sToServer = sOrigin = c(".chatInput textarea")[0].value;
                        sToClient = WUC.global.faceFilter(WUC.global.htmlFilter(sOrigin));
                        if (sOrigin != "") if (sOrigin != "\u8bf7\u5728\u8fd9\u91cc\u8f93\u5165\u5185\u5bb9\u3002") {
                            thisTime = (new Date).getTime();
                            if (typeof lastTime === "undefined") lastTime = thisTime = (new Date).getTime();
                            timeInterval = thisTime - lastTime;
                            if (timeInterval === 0 || timeInterval > 2E3) if (WUC.global.customLen(sOrigin) <= 140) if (this.msgTrim(sOrigin) !== d.Message.sPrevious) {
                                lastTime = (new Date).getTime();
                                d.Message.sPrevious = this.msgTrim(sOrigin);
                                f.send(sToServer);
                                c(".chatInput textarea")[0].value = "";
                                c(".chatInput textarea")[0].focus()
                            } else {
                                lastTime = (new Date).getTime();
                                d.Message.localHear(sToServer);
                                c(".chatInput textarea")[0].value = ""
                            } else d.Message.showError("overbrim");
                            else {
                                d.Message.showError("excessive");
                                c(".chatInput textarea")[0].focus()
                            }
                        } else {
                            d.Message.showError("vacant");
                            c(".chatInput textarea")[0].value = "";
                            c(".chatInput textarea")[0].focus()
                        } else {
                            d.Message.showError("vacant");
                            c(".chatInput textarea")[0].focus()
                        }
                    }
                },
                localHear: function (a) {
                    this.hear({
                        username: d.Base.me.username,
                        nick: d.Base.me.nick,
                        msg: a
                    })
                },
                hear: function (a) {
                    if (d.Base.checkBlock(a.username) == false) {
                        var b = f.getProfile(),
                            e = WUC.global.tolerantFilter(a.nick),
                            g = WUC.global.faceFilter(WUC.global.tolerantFilter(a.msg)),
                            k = "";
                        if (a.username === b.id) {
                                c(".chatBody ul").append('<li class="self"><div><span class="professor">' + e + ":</span><p>" + g + "</p></div></li>");
                                k = true
                            } else {
                                c(".chatBody ul").append('<li><div><a _forMenu="1" href="javascript:void(0);"><span class="visitor">' + e + ":</span></a><p>" + g + "</p></div></li>");
                                k = false;
                                this.chatBodyMenu(a.username, c(".chatBody ul"))
                            }
                        if (this.canRoll != false) c(".chatBody ul").get(0).scrollTop = c(".chatBody ul").get(0).scrollHeight;
                        WUC.witchChatShape && !a.isHistory && WUC.witchChatShape.newMiniMessage({
                                self: k,
                                name: e,
                                message: g
                            })
                    }
                },
                chatBodyMenu: function (a, b) {
                    var e = this.getMenuData(a),
                        g = this;
                    c(b).find("a[_forMenu]:last").eq(0).click(function (k) {
                            if (e && g.getMenuData(e.me.username)) {
                                k.preventDefault();
                                k.stopPropagation();
                                var l = 17 + c(this).width(),
                                    n = c(this).position().top;
                                e.showMenuFn.call(e.me, function (m) {
                                        g.canRoll = false;
                                        m.css({
                                            left: l + "px",
                                            top: n + "px"
                                        });
                                        m.show();
                                        m.unbind("click");
                                        m.bind("click", function () {
                                            m.unbind("click");
                                            c(this).hide();
                                            g.canRoll = true
                                        });
                                        var o = WUC.global.getEvent();
                                        if (o) if (o.stopPropagation) o.stopPropagation();
                                        else o.cancelBubble = true;
                                        c("body").one("click", function () {
                                            m.hide();
                                            g.canRoll = true
                                        })
                                    })
                            }
                        })
                },
                getMenuData: function (a) {
                    for (var b = d.Base.curRoom.memberList, e = 0; e < b.length; e++) if (a == b[e].username) return {
                        me: b[e],
                        blockState: b[e].isBlock,
                        showMenuFn: b[e].showCtrlMenu
                    }
                },
                unbindHideEmot: function () {
                    c(".emotDiv:visible").size() === 1 && c(".emotDiv").hide();
                    c(document).unbind("click", d.Message.unbindHideEmot)
                },
                loginDialog: function (a) {
                    switch (a) {
                    case "show":
                        c("#shadeDiv").show();
                        c("#loginWebuc").show();
                        c("#username")[0].focus();
                        break;
                    case "hide":
                        c("#shadeDiv").hide();
                        c("#loginWebuc").hide();
                        c("#username").val("");
                        c("#password").val("");
                        break
                    }
                },
                showNotice: function (a, b) {
                    if (b.length !== 0) {
                        var e = "";
                        switch (a) {
                        case "rename":
                            i = 0;
                            for (j = b.length; i < j; i++) e = i !== j - 1 ? e + b[i][0] + "\u628a\u540d\u5b57\u6539\u4e3a\u4e86" + b[i][1] + "\uff0c" : e + b[i][0] + "\u628a\u540d\u5b57\u6539\u4e3a\u4e86" + b[i][1] + "\u3002";
                            break;
                        case "arrive":
                            i = 0;
                            for (j = b.length; i < j; i++) e = i !== j - 1 ? e + b[i] + "\u3001" : e + b[i] + "\u8fdb\u5165\u4e86\u804a\u5929\u5ba4\u3002";
                            break;
                        case "depart":
                            i = 0;
                            for (j = b.length; i < j; i++) e = i !== j - 1 ? e + b[i] + "\u3001" : e + b[i] + "\u79bb\u5f00\u4e86\u804a\u5929\u5ba4\u3002";
                            break
                        }
                        e = WUC.global.tolerantFilter(e);
                        c(".chatBody ul").append('<li class="chatTips"><b></b><em>\u7cfb\u7edf\u516c\u544a\uff1a' + e + "</em></li>");
                        c(".chatBody ul").get(0).scrollTop = c(".chatBody ul").get(0).scrollHeight
                    }
                },
                warmTips: function (a) {
                    var b = "";
                    switch (a) {
                    case "join":
                        f.isLogin() || (b = "\u6b22\u8fce\u4f7f\u7528" + f.name + "\u804a\u5929\u5ba4\u3002");
                        break
                    }(b = WUC.global.tolerantFilter(b)) && c(".chatBody ul").append('<li class="chatTips"><b></b><em>\u6e29\u99a8\u63d0\u793a\uff1a' + b + "</em></li>");
                    c(".chatBody ul").get(0).scrollTop = c(".chatBody ul").get(0).scrollHeight
                },
                terminator: ""
            };
        d.ChatRoom = function (a) {
                this.id = Math.abs(a) || 0;
                this.title = "";
                this.boxEl = null;
                this.boxSize = [90, 30];
                this.curMember = null;
                this.num = {
                    all: 0,
                    login: 0
                };
                this.inList = false;
                this.chatList = [];
                this.memberList = [];
                this.blockList = []
            };
        d.ChatRoom.prototype = {
                init: function (a, b) {
                    this.id = a;
                    this.title = "\u623f\u95f4" + Math.abs(a);
                    if (b) {
                        this.inList = true;
                        this.boxEl = c("<a></a>").appendTo(d.Base.ui.roomListCtn).text(this.title).attr("href", "javascript:void(0)")
                    } else this.boxEl = c("<a></a>").appendTo(d.Base.ui.roomCtn).text(this.title).attr("href", "javascript:void(0)");
                    this.boxEl[0].hideFocus = true;
                    d.Base.curRoom && d.Base.curRoom.id == this.id && d.Base.atRoom(this.id)
                },
                renewNumber: function () {
                    this.num.login = d.Base.ui.signList.children().length;
                    this.num.all = this.num.login + d.Base.ui.noSignList.children().length;
                    d.Base.ui.signTitle.find("span").html("(" + this.num.login + ")");
                    d.Base.ui.noSignTitle.find("span").html("(" + (this.num.all - this.num.login) + ")");
                    d.Base.ui.memberListTitle.find("span").html(this.num.all)
                },
                addMember: function (a, b) {
                    if (b === "roster") {
                        this.clearAllMember();
                        this.memberList = []
                    }
                    if (typeof a !== "undefined") {
                        a instanceof Array || (a = [a]);
                        for (var e = [], g = 0, k = a.length; g < k; g++) if (!WUC.global.getItemByAttr(a[g].username, this.memberList, "username")) {
                            var l = new d.Member;
                            l.init(a[g]);
                            this.memberList.push(l);
                            e.push(a[g].nick);
                            if (d.Base.me.username === l.username) d.Base.me = l
                        }
                        b === "roster" && d.Base.me && d.Base.me.ui.el.appendTo(d.Base.me.ui.el.parent());
                        this.renewNumber()
                    }
                },
                removeMember: function (a) {
                    if (typeof a === "undefined") this.clearAllMember();
                    else {
                        a instanceof Array || (a = [a]);
                        for (var b = [], e = 0, g = a.length; e < g; e++) {
                            var k = WUC.global.getItemByAttr(a[e].username, this.memberList, "username");
                            if (k) {
                                b.push(a[e].nick);
                                WUC.global.delItemByAttr(a[e].username, d.Base.curRoom.blockList, "username");
                                this.memberList.splice(k.index, 1);
                                k.obj.ui.el.remove()
                            }
                        }
                        this.renewNumber()
                    }
                },
                clearAllMember: function () {
                    d.Base.ui.signList.html("");
                    d.Base.ui.noSignList.html("");
                    d.Base.curRoom.num.all = 0;
                    d.Base.curRoom.num.login = 0;
                    d.Base.ui.signTitle.find("span").html("");
                    d.Base.ui.noSignTitle.find("span").html("");
                    this.renewNumber()
                },
                reNameMember: function (a) {
                    a instanceof Array || (a = [a]);
                    for (var b = [], e = 0, g = a.length; e < g; e++) {
                        var k = WUC.global.getItemByAttr(a[e].username, this.memberList, "username");
                        a[e].username == d.Base.me.username && d.Addon.initName(a[e].nick);
                        if (k) {
                            k.obj.nick = a[e].nick;
                            k.obj.ui.nickEl.text(a[e].nick);
                            k.obj.ui.nickEl.attr("title", a[e].nick);
                            b.push([a[e].nickold, a[e].nick])
                        }
                    }
                    d.Message.showNotice("rename", b)
                }
            };
        d.Member = function () {
                this.nick = this.username = "";
                this.status = "online";
                this.isBlock = this.isLogin = false;
                this.ui = {
                    el: null,
                    nickEl: null
                }
            };
        d.Member.prototype = {
                init: function (a) {
                    for (var b in a) if (b === "group") this.isLogin = a.group == 1;
                    else this[b] = a[b];
                    if (this.nick) this.nick = this.nick.replace(/&amp;/g, "&").replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&apos;/g, "'").replace(/&quot;/g, '"');
                    this.ui.el = c("<li></li>");
                    this.ui.el.hover(function () {
                        c(this).addClass("listHover")
                    }, function () {
                        c(this).removeClass("listHover")
                    });
                    this.ui.nickEl = c('<a class="available" href="javascript:void(0)" title="' + this.nick + '"></a>').appendTo(this.ui.el);
                    this.ui.nickEl.text(this.nick);
                    d.Base.me && d.Base.me.username == this.username && this.ui.nickEl.addClass("signSel");
                    d.Base.curRoom.num.all++;
                    if (this.isLogin) {
                        d.Base.ui.signList.append(this.ui.el);
                        d.Base.curRoom.num.login++
                    } else d.Base.ui.noSignList.append(this.ui.el)
                },
                showCtrlMenu: function (a) {
                    if (this.username != d.Base.me.username) {
                        var b = d.Base.ui.dropMenu;
                        d.Base.ui.ctrlMenu[0].hide();
                        if (this.isBlock) {
                            d.Base.ui.ctrlMenu[1].hide();
                            d.Base.ui.ctrlMenu[2].show()
                        } else {
                            d.Base.ui.ctrlMenu[1].show();
                            d.Base.ui.ctrlMenu[2].hide()
                        }
                        if (typeof a == "function") a(b);
                        else {
                            a = this.ui.el.position().top + "px";
                            var e = this.ui.el.position().left - b.width() - 5 + "px";
                            b.show();
                            b.css({
                                top: a,
                                left: e
                            });
                            if (b = WUC.global.getEvent()) if (b.stopPropagation) b.stopPropagation();
                            else b.cancelBubble = true;
                            c("body").one("click", this.hideCtrlMenu)
                        }
                        d.Base.curRoom.curMember = this
                    }
                },
                hideCtrlMenu: function () {
                    d.Base.ui.dropMenu.hide();
                    d.Base.curRoom.curMember = null;
                    return false
                },
                block: function () {
                    if (this.isBlock) {
                        this.ui.el.find("span").remove();
                        this.isBlock = false;
                        WUC.global.delItemByAttr(this.username, d.Base.curRoom.blockList, "username")
                    } else {
                        this.ui.el.prepend(c("<span></span>"));
                        this.isBlock = true;
                        d.Base.curRoom.blockList.push(this)
                    }
                },
                chatWithMe: function () {
                    typeof ucClient !== "undefined" && ucClient.chatWith(this.username)
                }
            };
        d.setChatroomSize = function (a, b) {
                if (a < this.Base.chatRoomMiniSize.width) a = this.Base.chatRoomMiniSize.width;
                if (b < this.Base.chatRoomMiniSize.height) b = this.Base.chatRoomMiniSize.height;
                c("#CRloading").width(a).height(b);
                c(".initialize").width(a).height(b - 36);
                c(".initialize p").css("margin-top", b / 2 - 52);
                c("#ucChatRoom").width(a).height(b);
                c(".chatBody ul").height(b - 183);
                c(".chatBody").height(b - 183 + 12);
                c(".memberList").height(b - 133);
                c(".groupMember").height(b - 103);
                c(".hideGroupMember").css("margin-top", b / 2 - 56);
                c(".showGroupMember").css("margin-top", b / 2 - 56);
                c("#shadeDiv").width(a).height(b);
                c("#transparentDiv").height(b);
                c("#hideSelect").width(a + 1).height(b + 1);
                c(".searchMemberList .searchResult").height(b - 178)
            };
        d.Addon = {
                init: function () {
                    c("#LostService a").click(function () {
                        wuc_chatroom.reconnect();
                        d.Addon.reLink();
                        return false
                    });
                    c(".hideGroupMember").click(function () {
                        c(this).fadeOut("fast");
                        c(".groupMember").fadeOut("fast", function () {
                            c(".showGroupMember").show()
                        })
                    });
                    c(".hideGroupMember").mouseover(function () {
                        c(this).addClass("mHover")
                    }).mouseout(function () {
                        c(this).removeClass("mHover")
                    });
                    c(".showGroupMember").mouseover(function () {
                        c(this).addClass("mHover")
                    });
                    c(".showGroupMember").mouseout(function () {
                        c(this).removeClass("mHover")
                    });
                    c(".showGroupMember").click(function () {
                        f.getMember();
                        c(".showGroupMember").hide();
                        c(".hideGroupMember").fadeIn("fast");
                        c(".groupMember").fadeIn("fast")
                    });
                    c(".buttonArea span").hover(function () {
                        c(this).addClass("btnHover")
                    }, function () {
                        c(this).removeClass("btnHover")
                    });
                    c(".miniBar span").hover(function () {
                        c(this).addClass("mHover")
                    }, function () {
                        c(this).removeClass("mHover")
                    }).click(function () {
                        c("#miniChat").hide();
                        c("#ucChatRoom").show()
                    });
                    f.name != "" && d.Base.dragEvt();
                    c(".setMini").hover(function () {
                        c(this).addClass("miniHover")
                    }, function () {
                        c(this).removeClass("miniHover")
                    }).click(function () {
                        WUC.witchChatShape.showMiniChat()
                    });
                    c(".setClose").hover(function () {
                        c(this).addClass("closeHover")
                    }, function () {
                        c(this).removeClass("closeHover")
                    }).click(function () {
                        WUC.witchChatShape.callIndentMini();
                        c.cookie("crstatus", 1)
                    });
                    var a = c(".beforeLogin span"),
                        b = c(".beforeLogin span input");
                    a.mouseover(function () {
                            c(this).addClass("mHover")
                        }).mouseout(function () {
                            c(this).removeClass("mHover")
                        }).click(function () {
                            if (!c(this).hasClass("modify")) {
                                c(this).removeClass("mHover").addClass("modify");
                                b.show().val(c(".beforeLogin span u").text()).select()
                            }
                        });
                    b.keydown(function (e) {
                            if (e.keyCode === 13) {
                                d.Addon.setNickflag = 1;
                                d.Addon.setNick(b.val())
                            }
                        }).blur(function () {
                            if (d.Addon.setNickflag === 1) d.Addon.setNickflag = 0;
                            else d.Addon.setNick(b.val())
                        })
                },
                logoutToInit: function () {
                    c(".beforeLogin").show();
                    c(".beforeLogin span").hide();
                    c(".afterLogin").hide();
                    c(".initialize a").html("\u7acb\u5373\u53c2\u4e0e\u804a\u5929");
                    c(".initialize").show();
                    c(".chatInput textarea").get(0).value = "\u8bf7\u5728\u8fd9\u91cc\u8f93\u5165\u5185\u5bb9\u3002";
                    d.Base.dataReset()
                },
                lostTimer: null,
                countSeconds: 0,
                lostService: function (a) {
                    if (a > 0) {
                        d.Addon.lostTimer && clearInterval(d.Addon.lostTimer);
                        d.Addon.countSeconds = a;
                        c("#transparentDiv").show();
                        c("#LostService").hasClass("relink") && c("#LostService").removeClass("relink");
                        c("#LostService").show();
                        d.Addon.lostTimer = setInterval(d.Addon.countTime, 1E3);
                        c("#LostService u").text(a)
                    } else d.Addon.reLink()
                },
                countTime: function () {
                    c("#LostService u").text(--d.Addon.countSeconds);
                    d.Addon.countSeconds <= 0 && d.Addon.reLink()
                },
                reLink: function () {
                    d.Addon.lostTimer && clearInterval(d.Addon.lostTimer);
                    c("#transparentDiv").show();
                    c("#LostService").show();
                    !c("#LostService").hasClass("relink") && c("#LostService").addClass("relink")
                },
                reLinkSuccess: function () {
                    c("#LostService").hide();
                    c("#transparentDiv").hide()
                },
                checkWhatRoom: function () {
                    wuc_chatroom.name !== "" && c(".miniBar h4").text(f.name + "\u804a\u5929\u5ba4")
                },
                errorTips: function (a) {
                    var b = c(".errorTips");
                    b.show().html(a);
                    setTimeout(function () {
                        b.hide()
                    }, 1800);
                    c(".beforeLogin span input").select()
                },
                initName: function (a) {
                    c(".beforeLogin u").text(a)
                },
                setNickflag: 0,
                setNick: function (a) {
                    if (f.setNick(a) === 2) this.errorTips("\u79f0\u547c\u4e0d\u80fd\u4e3a\u7a7a\u3002");
                    else {
                        c(".beforeLogin span").removeClass("modify");
                        c(".beforeLogin span u").html(c(".beforeLogin span input").val());
                        c(".beforeLogin span input").hide()
                    }
                },
                joinCallback: function (a) {
                    if (a) {
                        c(".beforeLogin span").show();
                        c(".initialize").hide();
                        a = c("#ucChatDiv .chatView a").eq(0);
                        a[0] && a.remove()
                    } else alert("\u8fde\u63a5\u670d\u52a1\u5668\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5\u3002")
                },
                backToInit: function () {
                    c(".initialize a").html("\u60a8\u5df2\u7ecf\u5728\u5176\u4ed6\u804a\u5929\u5ba4\u767b\u5f55\uff0c\u53c2\u4e0e\u672c\u804a\u5929\u5ba4\u804a\u5929\u8bf7\u8fdb\u5165");
                    c(".initialize").show()
                },
                checkShow: function () {
                    if (f.isLogin()) {
                        c(".beforeLogin").hide();
                        c(".afterLogin").show();
                        f.name && c(".afterLogin").find("span").eq(0).html(f.name + "\u804a\u5929\u5ba4");
                        c("#myNickname").text("" + f.getProfile().nick)
                    }
                },
                keyTrigger: function (a, b) {
                    var e = c.wuc.keyTrigger.arguments,
                        g = c.wuc.keyTrigger.arguments.length,
                        k = false,
                        l = false,
                        n = window.Event ? true : false,
                        m = 0;
                    if (g > 2) k = e[2];
                    if (g > 3) l = e[3];
                    if (typeof a === "undefined") a = event;
                    if (k && !(typeof a.ctrlKey !== "undefined" ? a.ctrlKey : a.modifiers & Event.CONTROL_MASK > 0)) return false;
                    if (l && !(typeof a.altKey !== "undefined" ? a.altKey : a.modifiers & Event.ALT_MASK > 0)) return false;
                    m = n ? a.which : a.type === "keypress" || a.type === "keydown" ? a.keyCode : a.button;
                    return m === b
                },
                ctrlEnter: function (a) {
                    if (a.keyCode === 13) {
                        a.target.readOnly = true;
                        d.Message.say();
                        a.target.readOnly = false;
                        c("#ucChatRoom").focus();
                        c(".chatInput textarea").focus();
                        return false
                    }
                },
                enterKey: function (a) {
                    if (a.keyCode === 13) {
                        c("#loginWebuc span").css("visibility", "hidden");
                        a = c("#username").val();
                        var b = c("#password").val();
                        if (a === "") d.Message.showError("username");
                        else b === "" ? d.Message.showError("password") : sinaSSOController.login(a, b)
                    }
                },
                end: ""
            };
        d.AddEvent = {
                addOver: false,
                addAll: function (a) {
                    a || (a = false);
                    d.Base.clearFakeChatBox();
                    d.Base.init(a);
                    d.Addon.init();
                    WUC.searchMember.buildSearchMember({
                        onSearch: f.search,
                        getOutObj: function () {
                            return d
                        }
                    });
                    if (f.name !== "") {
                        WUC.witchChatShape.destroyMini();
                        WUC.witchChatShape.buildMiniChat({
                            hideChatBox: function () {
                                c("#ucChatRoom").hide();
                                d.Base.resetPopPosition(false)
                            },
                            showChatBox: function () {
                                var b = c("#ucChatRoom");
                                b.show();
                                d.Base.resetAllRooms();
                                d.Base.resetPopPosition(true);
                                c(".chatBody ul").get(0).scrollTop = c(".chatBody ul").get(0).scrollHeight;
                                c.browser.msie && c.browser.version === "6.0" && b.hide().show()
                            },
                            defaultTitle: f.name + "\u804a\u5929\u5ba4",
                            checkJoin: f.getRoomId
                        })
                    }
                    f.getProfileCookie().nick && c("#myNickname").text(f.getProfileCookie().nick);
                    c("#logout").click(function () {
                        if (confirm("\u60a8\u786e\u5b9a\u8981\u9000\u51fa\u5417\uff1f")) {
                            c("#message").hide();
                            c("#notice").hide();
                            try {
                                c.wim.close("message")
                            } catch (b) {}
                            d.AlertMessage.removeAll();
                            f.logout()
                        }
                        return false
                    });
                    c(".buttonArea a").click(function () {
                        if (c(".emotDiv:visible").size() === 0) {
                            c("div.emotDiv").show();
                            c(document).bind("click", d.Message.unbindHideEmot)
                        } else {
                            c("div.emotDiv").hide();
                            c(document).unbind("click", d.Message.unbindHideEmot)
                        }
                        c("div.emotDiv").get(0).rel = c(this).parent().parent().get(0).id;
                        return false
                    });
                    c(".emotDiv td").click(function () {
                        c(document).unbind("click", d.Message.unbindHideEmot);
                        c("div.emotDiv").hide();
                        if (c(".chatInput textarea").get(0).value === "\u8bf7\u5728\u8fd9\u91cc\u8f93\u5165\u5185\u5bb9\u3002") c(".chatInput textarea").get(0).value = this.abbr;
                        else WUC.global.insertAt(".chatInput textarea", this.abbr)
                    });
                    c(".buttonArea span").click(function () {
                        d.Message.say()
                    });
                    c(".chatInput textarea").keydown(function (b) {
                        return d.Addon.ctrlEnter(b)
                    });
                    c("#loginWebuc input").keyup(function (b) {
                        return d.Addon.enterKey(b)
                    });
                    c(".chatInput textarea").click(function () {
                        if (this.value === "\u8bf7\u5728\u8fd9\u91cc\u8f93\u5165\u5185\u5bb9\u3002") this.value = "";
                        c(this)[0].focus()
                    });
                    c("#loginWebuc .del").click(function () {
                        d.Message.loginDialog("hide");
                        return false
                    });
                    c(".initialize a").click(function () {
                        f.join();
                        return false
                    });
                    setTimeout(function () {
                        c("#CRloading").hide()
                    }, 1500);
                    d.Addon.checkWhatRoom();
                    d.Addon.checkShow();
                    d.setChatroomSize(f.chatRoomSize.width, f.chatRoomSize.height);
                    this.addOver = true
                }
            };
        c(function () {
                d.Base.beforeInit();
                if (f.name !== "") {
                    WUC.witchChatShape.buildMiniChat({
                        hideChatBox: function () {},
                        showChatBox: function () {
                            d.AddEvent.addAll();
                            c(".initialize a").click()
                        }
                    });
                    f.join()
                } else {
                    d.Base.fakeChatBox = c("<div/>");
                    var a = f.chatRoomSize.width,
                        b = f.chatRoomSize.height;
                    d.Base.fakeChatBox.css({
                            border: "1px solid #ADADAD",
                            "line-height": "22px",
                            width: a,
                            height: b,
                            "text-align": "center",
                            cursor: "pointer",
                            "font-size": 14
                        });
                    d.Base.fakeChatBox.html("<div style='padding-top:" + Math.round(b / 2 - 25) + "px'>\u7f51\u53cb\u70ed\u8bc4\uff0c\u5b9e\u65f6\u8bc4\u8bba<br><font style='color:#3B54B0;text-decoration:underline;'>\u7acb\u5373\u53c2\u4e0e\u804a\u5929</font></div>");
                    d.Base.fakeChatBox.one("click", function () {
                            c(this).remove();
                            d.AddEvent.addAll();
                            c(".initialize a").click()
                        });
                    d.Base.fakeChatBox.appendTo(c("#" + d.Base.webChatSpaceId));
                    b = a = null
                }
                d.AddEvent.addAll(true)
            });
        this.addRoom = function (a) {
                d.Base.addRoom(a)
            };
        this.setToRoom = function (a) {
                d.Base.atRoom(a)
            };
        this.changeRoom = function (a, b) {
                d.Base.changeRoom(a, b)
            };
        this.removeRoom = function (a) {
                d.Base.removeRoom(a)
            };
        this.memberIn = function (a, b) {
                d.Base.curRoom && d.Base.curRoom.addMember(a, b)
            };
        this.memberOut = function (a) {
                d.Base.curRoom && d.Base.curRoom.removeMember(a)
            };
        this.reName = function (a) {
                d.Base.curRoom && d.Base.curRoom.reNameMember(a)
            };
        this.getSelf = function (a) {
                d.Base.getSelf(a)
            };
        this.setCurRoom = function (a) {
                d.Base.setCurRoom(a)
            };
        this.say = function () {
                d.Message.say()
            };
        this.hear = function (a) {
                d.Message.hear(a)
            };
        this.showError = function (a) {
                d.Message.showError(a)
            };
        this.loginDialog = function (a) {
                d.Message.loginDialog(a)
            };
        this.showNotice = function (a, b) {
                d.Message.showNotice(a, b)
            };
        this.roomSwitch = function (a) {
                d.Message.roomSwitch(a)
            };
        this.setNick = d.Addon.setNick;
        this.initName = d.Addon.initName;
        this.joinCallback = d.Addon.joinCallback;
        this.checkShow = d.Addon.checkShow;
        this.setChatroomSize = d.setChatroomSize;
        this.backToInit = d.Addon.backToInit;
        this.lostService = d.Addon.lostService;
        this.reLinkSuccess = d.Addon.reLinkSuccess;
        this.logoutToInit = d.Addon.logoutToInit;
        this.reload = d.Base.reload;
        this.addAllEvent = d.AddEvent.addAll;
        this.AlertMessage = d.AlertMessage
    }
})(jQuery);
(function (e) {
    var y = {
        _loginFailed: "\u767b\u9646\u5931\u8d25,\u8bf7\u68c0\u67e5\u7f51\u7edc\u8fde\u63a5\u3002",
        _retryFailed: "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u6216\u5237\u65b0\u9875\u9762\u3002",
        _conflict: "\u60a8\u7684\u5e10\u53f7\u5728\u53e6\u4e00\u5730\u70b9\u767b\u5f55\u4e86\u540c\u4e00\u804a\u5929\u5ba4\uff0c\n\u60a8\u5df2\u88ab\u8feb\u4e0b\u7ebf\u3002",
        _loginFailedDebug: "\u767b\u9646\u5931\u8d25\u3002\n\u9519\u8bef\u4fe1\u606f:%msg%\n\u65f6\u95f4:%date%\u3002",
        _foo: ""
    };
    window.WUCChatroom = function (r) {
        function j(a, c) {
            if (s === i._cometStatus._connected) {
                g.publish(i._cometHandle, e.extend({
                    cmd: a
                }, c));
                return true
            } else return false
        }
        function l(a) {
            m = a;
            z();
            n._users.remove();
            B();
            t = false;
            C(D)
        }
        function v(a) {
            return a.substr(0, 1) != "u" ? i._userGroup._member : i._userGroup._visitor
        }
        function C(a) {
            function c(o) {
                o || (o = {});
                var f = i._nasServer.replace("{roomid}", A);
                if (m < 0) f += "&cid=" + m;
                if (o.uid && o.username) {
                    f += "&uid=" + o.uid + "&vnick=" + encodeURIComponent(o.username);
                    F = true
                } else if (e.cookie("vnick")) f += "&uid=0&vnick=" + encodeURIComponent(e.cookie("vnick"));
                e.getJSON(f, function (x) {
                    if (x && x.channel) {
                        d.joinCallback(1);
                        a(x)
                    } else d.joinCallback(0)
                })
            }
            if (_siteApi) {
                if (_siteApi.indexOf(window.location.protocol) == 0) {
                    var b = window.location.protocol.length + 3,
                        h = _siteApi.substr(b, _siteApi.indexOf("/", b)),
                        u = location.hostname;
                    if (h.indexOf("www.") == 0) h = h.substr(4);
                    if (u.indexOf("www.") == 0) u = u.substr(4);
                    _siteApi = h != u ? _siteApi + (_siteApi.indexOf("?") > -1 ? "&" : "?") + "callback=?" : _siteApi.substr(_siteApi.indexOf("/", b))
                }
                e.getJSON(_siteApi, {
                    r: Math.random()
                }, c)
            } else c()
        }
        function D(a) {
            if (a.channel) {
                g = new org.cometd.Cometd("chatroom");
                var c = a.server,
                    b = a.channel;
                m = a.cid;
                s = i._cometStatus._connecting;
                g.init({
                        url: c
                    });
                g.setBackoffIncrement(H);
                g.clearSubscriptions();
                g.clearListeners();
                s = i._cometStatus._connected;
                g.startBatch();
                g.publish(i._cometHandle, {
                        cmd: "authuser",
                        uid: a.uid,
                        ukey: a.ukey
                    });
                if (k) {
                        g.unsubscribe(k);
                        k = null
                    }
                k = g.subscribe(b, G);
                g.publish(i._cometHandle, {
                        cmd: "roomlist",
                        rid: A.toString(),
                        cid: ""
                    });
                g.publish(i._cometHandle, {
                        cmd: "vcard"
                    });
                g.endBatch();
                g.addListener("/meta/unsuccessful", function (h) {
                        w && e("<div><nobr>unsuccessful: " + JSON.stringify(h) + " backoff:" + g.getBackoffPeriod() + "</nobr></div>").appendTo("body");
                        d.lostService(g.getBackoffPeriod() / 1E3);
                        s = i._cometStatus._connecting
                    });
                g.addListener("/meta/connect", function (h) {
                        w && e("<div><nobr>connect: " + JSON.stringify(h) + "</nobr></div>").appendTo("body");
                        if (!h.failure) if (h.error && h.error === "402::Unknown client") {
                            d.AlertMessage.linkLose();
                            I = true
                        } else {
                            d.reLinkSuccess();
                            s = i._cometStatus._connected
                        }
                    });
                g.addListener("/meta/subscribe", function (h) {
                        w && e("<div><nobr>subscribe: " + JSON.stringify(h) + "</nobr></div>").appendTo("body")
                    });
                g.addListener("/meta/handshake", function (h) {
                        w && e("<div><nobr>handshake: " + JSON.stringify(h) + "</nobr></div>").appendTo("body");
                        if (h.reestablish && h.successful) {
                            if (k) {
                                g.unsubscribe(k);
                                k = null
                            }
                            k = g.subscribe(b, G)
                        }
                    });
                g.addListener("/meta/publish", function (h) {
                        w && e("<div><nobr>publish: " + JSON.stringify(h) + "</nobr></div>").appendTo("body")
                    })
            } else w && d.AlertMessage.show(y._loginFailedDebug.replace("%msg%", a.msg).replace("%date%", a.date), true)
        }
        function z() {
            if (g) {
                if (k) {
                    g.unsubscribe(k);
                    k = null
                }
                g.clearListeners();
                g.disconnect()
            }
        }
        function B() {
            p._users = [];
            p._rooms = [];
            p._messages = []
        }
        function G(a) {
            var c = a.data.type,
                b = a.data;
            if (w) {
                    console.debug(c);
                    console.debug(JSON.stringify(b))
                }
            switch (c) {
                case "roomlist":
                    if (t) if (b.rooms.length > 1) d.addRoom(b.rooms);
                    else {
                        a = b.rooms[0];
                        b = b.maxmember;
                        if (parseInt(a.c_ucount, 10) < parseInt(b, 10)) {
                            d.changeRoom(a.c_id, true);
                            l(a.c_id)
                        } else d.changeRoom(a.c_id, false)
                    } else e.each(b.rooms, function (o, f) {
                        p._rooms.push(f)
                    });
                    break;
                case "roomadd":
                    d.addRoom({
                        c_id: b.cid
                    });
                    break;
                case "roomdel":
                    d.removeRoom(b.cid);
                    break;
                case "setnick":
                    if (b.status == 3) {
                        e.cookie("vnick", b.nickold, {
                            path: "/"
                        });
                        d.initName(b.nickold);
                        d.showError("illegal")
                    } else if (b.nick !== b.nickold) {
                        d.reName(b);
                        n._users.update({
                            nick: b.nick
                        }, {
                            username: b.username
                        });
                        if (b.username === q.id) {
                            q.nick = b.nick;
                            e.cookie("vnick", b.nick, {
                                path: "/"
                            })
                        }
                    }
                    break;
                case "msg":
                    e.each(b.msgs, function (o, f) {
                        d.hear(f);
                        f.status == 3 && d.showError("sensitive")
                    });
                    break;
                case "lastmsg":
                    e.each(b.msgs, function (o, f) {
                        f.isHistory = true;
                        d.hear(f);
                        f.status == 3 && d.showError("sensitive")
                    });
                    break;
                case "roster":
                    e.each(b.groups[0].items, function (o, f) {
                        f.group = v(f.username);
                        f.joinTime = (new Date).getTime();
                        n._users.find({
                            username: f.username
                        }).length === 0 && n._users.remove({
                            username: f.username
                        });
                        n._users.insert(f);
                        t || p._users.push(f)
                    });
                    t && d.memberIn(b.groups[0].items, "roster");
                    break;
                case "join":
                    if (q.id === 0) return;
                    else {
                        var h = [];
                        e.each(b.usernames, function (o, f) {
                            f.group = v(f.username);
                            f.joinTime = (new Date).getTime();
                            n._users.find({
                                username: f.username
                            }).length === 0 && n._users.insert(f);
                            h.push(f)
                        });
                        if (t) d.memberIn(h, "join");
                        else p._users = p._users.concat(h)
                    }
                    break;
                case "exit":
                    e.each(b.usernames, function (o, f) {
                        var x = f.username;
                        if (x != q.id) {
                            d.memberOut(f);
                            n._users.remove({
                                username: x
                            })
                        }
                    });
                    break;
                case "conflict":
                    z();
                    d.backToInit();
                    break;
                case "vcard":
                    q.id = b.username;
                    q.nick = b.nick;
                    q.group = v(b.username);
                    e.cookie("vnick") || e.cookie("vnick", b.nick, {
                        path: "/"
                    });
                    d.getSelf(b);
                    setTimeout(function () {
                        d.checkShow()
                    }, 1E3);
                    break
                }
            if (!t) {
                    E.push(c);
                    c = ["roomlist", "vcard"];
                    b = a = 0;
                    for (var u = c.length; b < u; b++) e.inArray(c[b], E) !== -1 && a++;
                    if (a === u) {
                        t = true;
                        E = [];
                        d.addRoom(p._rooms);
                        d.setCurRoom(m);
                        p.rooms = [];
                        if (p._users.length > 0) {
                            d.memberIn(p._users, "roster");
                            p._users = []
                        }
                    }
                }
        }
        var A = r.id || 1E6;
        this.id = A;
        this.name = r.name || "";
        this.siteApi = _siteApi = e.trim(r.siteApi || "");
        this.logo = r.logo;
        this.logGroupName = r.logGroupName || "\u767b\u5f55\u7528\u6237";
        this.chatRoomSize = {
            width: 450,
            height: 375
        };
        var m = function () {
            var a = 0,
                c = /^.+#cid=(-\d+).*$/;
            if (c.test(location.href)) a = location.href.replace(c, "$1");
            return a
        }(),
            d = new WUC.ChatRoom_UI(this),
            g = null,
            H = 5E3,
            I = false,
            s, k = null,
            w = false,
            t = false,
            E = [],
            F = false,
            i = {
                _nasServer: "http://nas.uc.sina.com.cn/webroom/?type=finance&APP_KEY=12000&roomid={roomid}&callback=?",
                _cometHandle: "/im/req",
                _userGroup: {
                    _member: 1,
                    _visitor: 2
                },
                _cometStatus: {
                    _connecting: 1,
                    _connected: 2,
                    _disconnecting: 3,
                    _disconnect: 4
                }
            },
            q = {
                id: 0,
                nick: "",
                group: i._userGroup._visitor
            },
            p = {
                _users: [],
                _rooms: [],
                _messages: []
            },
            n = {
                _users: new TAFFY([])
            };
        this.getClassName = function () {
                return "WUCChatroom"
            };
        this.isLogin = function () {
                return F
            };
        this.getProfileName = function () {
                return this.getProfileCookie().nick || ""
            };
        this.logout = function () {
                sinaSSOController.logout();
                this.afterLogout()
            };
        this.logoutCallback = function () {
                m = 0;
                z();
                n._users.remove();
                B();
                t = false;
                var a = /#cid=-\d+/ig;
                if (a.test(location.href)) location.href = location.href.replace(a, "#cid=0");
                d.logoutToInit()
            };
        this.login = function (a, c) {
                sinaSSOController.login(a, c)
            };
        this.loginCallback = function () {
                q = {};
                m < 0 && l(m);
                d.checkShow();
                d.loginDialog("hide")
            };
        this.setRoomId = function (a) {
                m = a
            };
        this.getRoomId = function () {
                return m
            };
        this.getProfile = function () {
                return q
            };
        this.join = function () {
                C(D)
            };
        this.search = function (a) {
                return n._users.get({
                    nick: {
                        like: a
                    },
                    username: {
                        "!is": q.id
                    }
                })
            };
        this.send = function (a) {
                if (!j("msg", {
                    msg: a
                })) {
                    d.AlertMessage.show("\u53d1\u9001\u6d88\u606f\u5931\u8d25\u3002", true);
                    return false
                }
                return true
            };
        this.getDefaultMsg = function (a) {
                if (!j("lastmsg", {
                    count: a ? a : 20
                })) {
                    d.AlertMessage.show("\u83b7\u53d6\u9ed8\u8ba4\u4fe1\u606f\u5931\u8d25\u3002", true);
                    return false
                }
                return true
            };
        this.setNick = function (a) {
                var c = 0;
                a = e.trim(a);
                if (/^\s*$/.test(a)) c = 2;
                else if (a === q.nick) c = 3;
                else if (q.group === i._userGroup._member) c = 4;
                else j("setnick", {
                    nick: a
                });
                return c
            };
        this.getProfileCookie = function () {
                var a = {},
                    c = e.cookie("SUP");
                if (c) {
                        c = c.split("&");
                        if (c.length) {
                            var b;
                            e.each(c, function (h, u) {
                                b = u.split("=", 2);
                                a[b[0]] = decodeURIComponent(b[1].replace(/\+/ig, "%20")).replace(/&lt;/ig, "<").replace(/&gt;/ig, ">")
                            })
                        }
                    }
                return a
            };
        this.showCache = function () {
                d.AlertMessage.show(JSON.stringify(n._users.get()), true)
            };
        this.change = function (a) {
                m !== a && j("roomlist", {
                    rid: A.toString(),
                    cid: a.toString()
                })
            };
        this.reconnect = function () {
                g.reconnect()
            };
        this.getMember = function () {
                g.publish(i._cometHandle, {
                    cmd: "roster",
                    type: "all"
                })
            };
        this.setChatroomSize = function (a, c) {
                a = Math.floor(a);
                c = Math.floor(c);
                if (!isNaN(a) && !isNaN(c)) this.chatRoomSize = {
                    width: a,
                    height: c
                }
            };
        e(window).bind("unload", function () {
                if (s === i._cometStatus._connected || s === i._cometStatus._connecting) {
                    s = i._cometStatus._disconnecting;
                    try {
                        if (k) {
                            g.unsubscribe(k);
                            k = null
                        }
                        g.disconnect()
                    } catch (a) {}
                    s = i._cometStatus._disconnect
                }
            });
        e(document).ready(function () {
                comet = {};
                if (navigator.appVersion.indexOf("MSIE") != -1) {
                    comet.connection = new ActiveXObject("htmlfile");
                    comet.connection.open();
                    comet.connection.write("<html>");
                    comet.connection.write("<script>document.domain = '" + document.domain + "'<\/script>");
                    comet.connection.write("</html>");
                    comet.connection.close();
                    comet.iframediv = comet.connection.createElement("div");
                    comet.connection.appendChild(comet.iframediv);
                    comet.connection.parentWindow.comet = comet;
                    comet.iframediv.innerHTML = "<iframe id='cometd_chatroom'></iframe>"
                } else {
                    if (navigator.appVersion.indexOf("KHTML") != -1) {
                        comet.connection = document.createElement("iframe");
                        comet.connection.setAttribute("id", "cometd_chatroom");
                        with(comet.connection.style) {
                            position = "absolute";
                            left = top = "-100px";
                            height = width = "1px";
                            visibility = "hidden"
                        }
                    } else {
                        comet.connection = document.createElement("iframe");
                        comet.connection.setAttribute("id", "cometd_chatroom");
                        with(comet.connection.style) {
                            left = top = "-100px";
                            height = width = "1px";
                            visibility = "hidden";
                            display = "none"
                        }
                        comet.iframediv = document.createElement("iframe");
                        comet.connection.appendChild(comet.iframediv)
                    }
                    document.body.appendChild(comet.connection)
                }
                comet = null;
                m < 0 && C(D);
                WUC.witchChatShape.setDefualtNum({
                    defaultName: r.name
                })
            });
        e(window).load(function () {
                document.title = document.title.replace(/#cid=[-|0-9]*/g, "")
            });
        this.reload = function () {
                z();
                n._users.remove();
                B();
                t = false;
                ucClient && ucClient.login();
                this.loginCallback();
                this.afterLogin()
            };
        this.afterLogin = function () {};
        this.afterLogout = function () {}
    }
})(jQuery);
Math.uuid = function () {
    var e = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
    return function (y, r) {
        var j = [];
        r = r || e.length;
        if (y) for (var l = 0; l < y; l++) j[l] = e[0 | Math.random() * r];
        else {
            var v;
            j[8] = j[13] = j[18] = j[23] = "-";
            j[14] = "4";
            for (l = 0; l < 36; l++) if (!j[l]) {
                v = 0 | Math.random() * 16;
                j[l] = e[l == 19 ? v & 3 | 8 : v]
            }
        }
        return j.join("")
    }
}();