require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(beyonwizt4)$"

SRCDATE = "20180324"

SRC_URI[md5sum] = "edcd79fca19dee3f47d757dd2d864a20"
SRC_URI[sha256sum] = "828cad036e93703099c38706bc61e0bfe2b64ed89b5fbf933d221dc3cdb8bac8"

SRC_URI = "http://source.mynonpublic.com/ini/ini-840t4-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra/$i.ko
        echo $i >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
