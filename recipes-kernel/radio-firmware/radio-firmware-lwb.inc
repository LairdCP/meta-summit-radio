include radio-firmware.inc

LICENSE = "Laird"
NO_GENERIC_LICENSE[Laird] = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=53d3628b28a0bc3caea61587feade5f9"

LWB_REGDOMAIN ?= ""

do_install() {
	install -d  ${D}${nonarch_base_libdir}
	cp -r ${S}/lib/* ${D}${nonarch_base_libdir}
    if [ ! -z ${LWB_REGDOMAIN} ]; then
	   install -d  ${D}${sysconfdir}/modprobe.d/
       echo "options brcmfmac regdomain=\"${LWB_REGDOMAIN}\"" >> ${D}${sysconfdir}/modprobe.d/brcmfmac.conf
    fi
}
