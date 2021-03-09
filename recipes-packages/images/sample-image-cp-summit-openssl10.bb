DESCRIPTION = "CP Summit Sample Image"
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
	net-config \
	usb-gadget \
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
	kernel-module-summit-backports-laird \
	summit60-firmware-sdio-sdio \
	summit-supplicant-openssl10 \
	adaptive-ww-openssl10 \
	laird-networkmanager \
	"
