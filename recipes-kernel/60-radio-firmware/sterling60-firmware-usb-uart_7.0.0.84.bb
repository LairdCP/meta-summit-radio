SUMMARY = "Laird Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-usb-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "ae3187d7f111b43b0a8199c50d19e1d9"
SRC_URI[sha256sum] = "e807cbdd6ba27cc8d24186785bba5aa92c00c5dd4fdca4a113b9207297b3c5c1"

include radio-firmware.inc
