require radio-firmware.inc radio-stack-lwb-version.inc

LICENSE = "Ezurio"
NO_GENERIC_LICENSE[Laird] = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=202fa756612e170e23ac19b6348a335b"

FILES:${PN} += "${sysconfdir}"

LWB_REGDOMAIN ?= ""

do_install:append() {
    if [ ! -z ${LWB_REGDOMAIN} ]; then
        install -d  ${D}${sysconfdir}/modprobe.d
        echo "options brcmfmac regdomain=\"${LWB_REGDOMAIN}\"" > ${D}${sysconfdir}/modprobe.d/brcmfmac_regd.conf
    fi
}
