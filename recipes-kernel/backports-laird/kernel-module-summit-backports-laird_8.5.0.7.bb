SUMMARY = "Laird Connectivity Backports for Summit 60"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-summit60','defconfig-summit60_nbt',d)} \
"

SRC_URI += "${LRD_60_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "1d858a6072a8e304f8663281af42f7e9"
SRC_URI[sha256sum] = "0c72fb63d520ab442a951f5c37b25bb8e8618b225ee5694a14db9c0274148641"

RCONFLICTS_${PN} = " \
        kernel-module-sterling-backports-laird \
        kernel-module-lwb-backports-laird \
        kernel-module-lwb5p-backports-laird \
        "

include backports-laird.inc
