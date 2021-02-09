SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "d61742ea4a3a44cd44a95d5b86cbdad8"
SRC_URI[sha256sum] = "4a0c5de89b3328c047915275ec495a751d8c98f15d71ec2abfbf5a44fd4b8c6d"

include radio-firmware.inc
