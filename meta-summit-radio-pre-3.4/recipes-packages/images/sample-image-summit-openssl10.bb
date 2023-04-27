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
	htop \
	ethtool \
	iperf3 \
	tcpdump \
	iw \
	kernel-module-su60-backports-summit \
	summit60-firmware-sdio-sdio \
	summit-supplicant-openssl10 \
	adaptive-ww \
	summit-networkmanager-su60 \
	summit-networkmanager-su60-nmcli \
	"
