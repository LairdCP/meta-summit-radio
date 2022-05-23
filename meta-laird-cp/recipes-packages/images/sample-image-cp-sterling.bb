DESCRIPTION = "Sterling 60 Series (SDIO/SDIO) sample image"
LICENSE = "MIT"

inherit core-image

export IMAGE_BASENAME = "${PN}"

IMAGE_FEATURES += "\
	ssh-server-dropbear \
	splash \
	"

IMAGE_FEATURES:remove = "\
	tools-profile \
	tools-debug \
	tools-testapps \
	"

IMAGE_INSTALL += "\
	iproute2 \
	rng-tools \
	ca-certificates \
	tzdata \
	alsa-utils \
	htop \
	ethtool \
	iperf3 \
	tcpdump \
	iw \
	kernel-module-sterling-backports-laird \
	sterling60-firmware-sdio-sdio \
	sterling-supplicant \
	lrd-networkmanager-sterling \
	lrd-networkmanager-sterling-nmcli \
	"
