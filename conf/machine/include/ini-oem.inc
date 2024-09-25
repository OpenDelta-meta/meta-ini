# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED += "rtc"

BOX_BRAND = "ini"

KERNEL_FILE = "kernel.bin"
ROOTFS_FILE = "rootfs.bin"

DVBPROVIDER = "kernel"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
	gstreamer1.0-plugin-dvbmediasink \
	kernel-module-rtl8192cu \
	firmware-rtl8192cu \
	firmware-rtl8192cufw \
	"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-dts"

PREFERRED_PROVIDER_virtual/blindscan-dvbs = "ini-blindscan-utils"
PREFERRED_PROVIDER_virtual/blindscan-dvbc = "ini-blindscan-utils"

KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"
KERNEL_CONSOLE = "ttyS0,115200n8"

IMAGE_FSTYPES ?= "ubi"

IMAGE_CMD_ubi_prepend = "\
	rm -rf ${IMAGE_ROOTFS}/boot/*; \
	rm -rf ${IMAGE_ROOTFS}/tmp/*; \
	"

IMAGE_CMD_ubi_append = " \
	mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
	${@bb.utils.contains("MACHINE_FEATURES", "extrasplash", "cp ${DEPLOY_DIR_IMAGE}/cfe.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/;" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "extrasplash", "cp ${DEPLOY_DIR_IMAGE}/splash.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/;" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "extrasplash", "cp ${DEPLOY_DIR_IMAGE}/imageversion.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/;" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "extrasplash", "cp ${DEPLOY_DIR_IMAGE}/kernel.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/;" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "extrasplash", "cp ${DEPLOY_DIR_IMAGE}/rootfs.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/;" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "extrasplash", "cp ${DEPLOY_DIR_IMAGE}/finished.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/;" , "", d)} \
	mv ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ubi ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${ROOTFS_FILE}; \
	gzip -f -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${KERNEL_FILE}; \
	echo ${IMAGE_NAME} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
	${@bb.utils.contains('MACHINE_FEATURES', 'forceflash', \
	'echo "dont change this file" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/force;' , \
	'echo "rename this file to force to force an update without confirmation" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/noforce;', d)} \
	rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
	cd ${DEPLOY_DIR_IMAGE}; \
	zip ${IMAGE_NAME}_usb.zip ${IMAGEDIR}/*; \
	rm -f ${DEPLOY_DIR_IMAGE}/*.ubi; \
	rm -f ${DEPLOY_DIR_IMAGE}/*.ubifs; \
	rm -rf ${IMAGEDIR}; \
	rm -rf atemio; \
	rm -rf miraclebox; \
	rm -rf beyonwiz; \
	rm -rf bwidowx; \
	rm -rf bwidowx2; \
	rm -rf opticum; \
	"

MACHINE_FEATURES += "alsa hdmicec switchoff usbhost osdposition micom HDMI RCA"

# Broken bootloader can only flash around 100 MB
FLASHSIZE = "96"

BOOTLOGO_FILENAME = "splash.bin"

BLINDSCAN_BINARY = "ini_blindscan"

require conf/machine/include/tune-mips32.inc
require conf/machine/include/soc-family.inc

RCNAME ?= "ini4"
