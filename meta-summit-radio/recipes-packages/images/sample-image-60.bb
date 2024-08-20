DESCRIPTION = "60 Series (PCIE/UART) sample image"
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
	ca-certificates \
	tzdata \
	htop \
	ethtool \
	iperf3 \
	tcpdump \
	iw \
	kernel-module-60-backports \
	60-radio-firmware-pcie-uart \
	${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'summit-adaptive-bt summit-bt-uart-scripts-60', '', d)} \
	summit-supplicant-60 \
	summit-supplicant-libs-60 \
	summit-adaptive-ww \
	summit-networkmanager-60 \
	summit-networkmanager-60-nmcli \
	"
