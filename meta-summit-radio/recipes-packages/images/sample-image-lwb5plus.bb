DESCRIPTION = "Sterling LWB5+ SDIO/UART M.2 (diversity antenna) sample image"
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
	htop \
	ethtool \
	iperf3 \
	tcpdump \
	iw \
	kernel-module-lwb-backports-summit \
	lwb5plus-sdio-div-firmware \
	summit-supplicant-lwb \
	summit-supplicant-libs-lwb \
	summit-networkmanager-lwb \
	summit-networkmanager-lwb-nmcli \
	"
