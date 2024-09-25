require conf/machine/include/ini-oem.inc

KERNELVERSION = "3.6.0"

MTD_KERNEL = "mtd1"
MTD_ROOTFS = "mtd2"

PREFERRED_PROVIDER_virtual/kernel = "linux-ini-hdx"
PREFERRED_VERSION_linux-ini-hdx = "${KERNELVERSION}"

MACHINE_FEATURES += "textlcd fanctrl extrasplash blindscan-dvbc blindscan-dvbs ci dvb-c SCART YUV"

SOC_FAMILY = "bcm7413"

MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
	ini-dvb-modules-inihdx \
	ini-cfe \
	"

STB_PLATFORM = "inihdx"
