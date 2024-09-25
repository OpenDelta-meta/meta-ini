require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(beyonwizt2)$"

SRCDATE = "20180324"

SRC_URI[md5sum] = "9d99aeed41f43aafa027d5769be26536"
SRC_URI[sha256sum] = "7e488fc0f99d8e458d7bb07d84df56fec50023c5bafee0b1a394c42044b9ed5b"

SRC_URI = "http://source.mynonpublic.com/ini/ini-442dt-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra/${i}_${MACHINE}.ko
        echo ${i}_${MACHINE} >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
