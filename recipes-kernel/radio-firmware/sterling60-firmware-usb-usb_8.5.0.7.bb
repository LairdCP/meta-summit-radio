SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "fcbc85042a5835e88b6ef8ccbeb66d5b"
SRC_URI[sha256sum] = "d1ad2fd7021895959868c125568d7ff8d9ee96f03f6677de6973df91e0339748"

include radio-firmware-60.inc
