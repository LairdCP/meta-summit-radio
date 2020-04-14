DESCRIPTION = "Sterling Sample Image"
LICENSE = "MIT"

inherit core-image

export IMAGE_BASENAME = "${PN}"

IMAGE_FEATURES += "\
	ssh-server-dropbear \
	splash \
	"

IMAGE_FEATURES_remove = "\
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
	sterling-backports-laird \
	sterling60-firmware-sdio-sdio \
	sterling60-firmware-sdio-uart \
	sterling60-firmware-pcie-uart \
	sterling60-firmware-pcie-usb \
	sterling60-firmware-usb-uart \
	sterling60-firmware-usb-usb \
	sterling-supplicant \
	laird-networkmanager \
	"
