require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(xpeedlx|sezam1000hd|mbmini|atemio5x00)$"

SRCDATE = "20160122"

SRC_URI[md5sum] = "efc03d2687c7204a2475e8b727ce67eb"
SRC_URI[sha256sum] = "f2acb694b38f6ab90c79cb7ef4650253c2b204ad611cdf9ad22469a7850e3af7"

SRC_URI = "http://source.mynonpublic.com/ini/ini-1000-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra
        echo $i >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
