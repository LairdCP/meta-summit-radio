SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "47ebdb409a2df62a49b961d167490229"
SRC_URI[sha256sum] = "a34d001064e54fb0a739e7811ae7507bd3eece37b5573828409de895c4fe2125"

include radio-firmware-60.inc
