require conf/machine/include/ini-oem.inc

KERNELVERSION = "3.14.2"

MTD_KERNEL = "mtd2"
MTD_ROOTFS = "mtd0"

PREFERRED_PROVIDER_virtual/kernel = "linux-ini-hdp"
PREFERRED_VERSION_linux-ini-hdp = "${KERNELVERSION}"

MACHINE_FEATURES += "videoenhancement extrasplash blindscan-tbs dvb-c blindscan-dvbc blindscan-dvbs multitranscoding transcoding bwlcd255 fanctrl fan wol ci YUV HDMI-IN-HD"

SOC_FAMILY = "bcm7425"

MKUBIFS_ARGS = "-m 4096 -e 1040384 -c 1984"
UBINIZE_ARGS = "-m 4096 -p 1024KiB"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
	ini-coldboot \
	ini-ethwol \
	ini-cfe \
	"

STB_PLATFORM = "inihdp"
