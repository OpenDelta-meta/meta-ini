require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(bwidowx)$"

SRCDATE = "20160122"

SRC_URI[md5sum] = "666809b5d75dd426f0701f24663951ab"
SRC_URI[sha256sum] = "443c472aa9a66f9b7cf76fc260f4a9524e9831a5ea189d745e06b520a14d9ffd"

SRC_URI = "http://source.mynonpublic.com/ini/yhgd2580-7358-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra/$i.ko
        echo $i >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
