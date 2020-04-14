SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "848265dad96ec9794374d928f71156ec"
SRC_URI[sha256sum] = "0f34e363d631b3c324834e989e801e141cea4a916e756f6749a2253edcc60722"

include radio-firmware.inc
