SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "fa5d227f70253fa7b83d54a1d97af660"
SRC_URI[sha256sum] = "68893b8236d0d4a5169f5fb636f36796335ea66cb5d20b15095cf23cf8a19d56"

include radio-firmware.inc
