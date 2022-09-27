DESCRIPTION = "Sterling 60 Series (SDIO/SDIO) sample image"
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
	htop \
	ethtool \
	iperf3 \
	tcpdump \
	iw \
	kernel-module-st60-backports-summit \
	sterling60-firmware-sdio-sdio \
	sterling-supplicant-st60 \
	summit-networkmanager-st60 \
	summit-networkmanager-st60-nmcli \
	"
