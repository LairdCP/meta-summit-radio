require mfg-reg.inc
require radio-stack-lwb-version.inc

do_install() {
	install -D -m 775 ${B}/lru ${D}${bindir}/lru

	if ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'true', 'false', d)} && [ -f ${B}/btlru ]; then
		install -D -m 775 ${B}/btlru ${D}${bindir}/btlru
	fi

	if ls ${B}/brcmfmac* >/dev/null 2>&1 ; then
		install -D -m 644 -t ${D}${nonarch_base_libdir}/firmware/brcfmac ${B}/brcmfmac*
	fi
	if ls ${B}/cyfmac* >/dev/null 2>&1 ; then
		install -D -m 644 -t ${D}${nonarch_base_libdir}/firmware/cypress ${B}/cyfmac*
	fi
}