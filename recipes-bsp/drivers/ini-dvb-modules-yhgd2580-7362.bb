require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(bwidowx2)$"

SRCDATE = "20160122"

SRC_URI[md5sum] = "ae1311220a63da86e0f0c6ce53b017f0"
SRC_URI[sha256sum] = "68346874ec086d96f11e1bc2189337ec5c0ec86e6deb59a069c07e59b43882d4"

SRC_URI = "http://source.mynonpublic.com/ini/yhgd2580-7362-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra/${i}_${MACHINE}.ko
        echo ${i}_${MACHINE} >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
