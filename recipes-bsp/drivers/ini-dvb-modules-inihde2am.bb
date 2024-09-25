require ini-dvb-modules.inc

COMPATIBLE_MACHINE = "^(atemio6200|atemio6100|atemio6000)$"

SRCDATE = "20160122"

SRC_URI[md5sum] = "d337a12a12849d3b96e12d5ad580fea3"
SRC_URI[sha256sum] = "1877059e3c53d7d5c66e0578d0e4efdff4ace03ecd54cec319be09a00e65f99d"

SRC_URI = "http://source.mynonpublic.com/ini/ini-422-drivers-${KV}-${SRCDATE}.zip"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra/${i}_${MACHINE}.ko
        echo ${i}_${MACHINE} >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
    done
}
