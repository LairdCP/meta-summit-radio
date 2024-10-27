require mfg-reg.inc

REG_NAME = "mfg60n"

do_install() {
	install -D -m 775 -t "${D}${bindir}" "${B}/lru" "${B}/lmu"

	if ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'true', 'false', d)}; then
		install -D -m 775 "${B}/btlru" "${D}${bindir}/btlru"
	fi

	install -D -m 644 -t "${D}${nonarch_base_libdir}/firmware/lrdmwl" "${B}/88W8997_mfg_"*
}
