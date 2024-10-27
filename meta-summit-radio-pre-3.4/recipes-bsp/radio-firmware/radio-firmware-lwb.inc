require radio-firmware.inc
require radio-stack-lwb-version.inc

LICENSE += "& Cypress"
NO_GENERIC_LICENSE[Cypress] = "LICENSE.cypress"
LIC_FILES_CHKSUM += "file://LICENSE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7"

FILES_${PN} += "${sysconfdir}"

LWB_REGDOMAIN ?= ""

do_install_append() {
    if [ ! -z "${LWB_REGDOMAIN}" ]; then
        install -d  "${D}${sysconfdir}/modprobe.d"
        echo "options brcmfmac regdomain=\"${LWB_REGDOMAIN}\"" > "${D}${sysconfdir}/modprobe.d/brcmfmac_regd.conf"
    fi
}
