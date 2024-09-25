require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(opticumtt)$"

SRCDATE = "20160122"

SRC_URI[md5sum] = "3469634ce63a6f121b905dec81b8bd51"
SRC_URI[sha256sum] = "bf486def7044289debbd7d6017cd2d04ea9bd5eba91eec9b89c951af6654904e"

SRC_URI = "http://source.mynonpublic.com/ini/ini-435oc-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra/${i}_${MACHINE}.ko
        echo ${i}_${MACHINE} >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
