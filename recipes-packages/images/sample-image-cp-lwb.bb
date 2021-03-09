DESCRIPTION = "CP LWB Sample Image"
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
	kernel-module-lwb-backports-laird \
	lwb-fcc-firmware \
	sterling-supplicant-lwb \
	"
