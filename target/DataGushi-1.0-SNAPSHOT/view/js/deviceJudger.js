function deviceJudge(url) {
    var ua =  navigator.userAgent;
    isAndroid = /Android/i.test(ua);
    isBlackBerry = /BlackBerry/i.test(ua);
    isWindowPhone = /IEMobile/i.test(ua);
    isIOS = /iPhone|iPad|iPod/i.test(ua);
    isMobile = isAndroid || isBlackBerry || isWindowPhone || isIOS;
    if(isAndroid) isMobile = 'true';
    if(isBlackBerry) isMobile = 'true';
    if(isWindowPhone) isMobile = 'true';
    if(isIOS) isMobile = 'true';
    if(isMobile)    {
        window.location.href=url;
    }
}
