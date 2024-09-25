require conf/machine/include/ini-oem.inc

KERNELVERSION = "3.12.1"

MTD_KERNEL = "mtd2"
MTD_ROOTFS = "mtd0"

PREFERRED_PROVIDER_virtual/kernel = "linux-ini-hde"
PREFERRED_VERSION_linux-ini-hde2 = "${KERNELVERSION}"

MACHINE_FEATURES += "videoenhancement extrasplash blindscan-tbs blindscan-dvbs blindscan-dvbc dvb-c ci"

SOC_FAMILY = "bcm7358"

MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
	ini-cfe \
	"

STB_PLATFORM = "inihde"
