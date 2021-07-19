SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "013f86378b2d86de9e8ec81e83705a0d"
SRC_URI[sha256sum] = "fb81470ed96edc8ae29723e0ecc4fa31f650fb26bf94df83cf9a19e13b7e521f"

include radio-firmware-60.inc
