require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(ventonhdx|sezam5000hd|mbtwin|beyonwizt3)$"

SRCDATE = "20160406"

SRC_URI[md5sum] = "35c14df7d4fefdae315fb9bc64a8b006"
SRC_URI[sha256sum] = "df587ae3e817cb07f4303c4bac9b0ad58a25cd362f2392ddb19776e629e3703e"

SRC_URI = "http://source.mynonpublic.com/ini/ini-x000-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra
        echo $i >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
