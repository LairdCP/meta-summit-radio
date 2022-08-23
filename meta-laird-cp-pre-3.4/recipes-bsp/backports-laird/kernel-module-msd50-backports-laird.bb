SUMMARY = "Laird Connectivity Backports for MSD50"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-wb50n','defconfig-wb50n_nbt',d)} \
	"

RCONFLICTS_${PN} = " \
        kernel-module-summit-backports-laird \
        kernel-module-sterling-backports-laird \
        kernel-module-lwb-backports-laird \
        kernel-module-lwb5p-backports-laird \
        "

module_conf_ath6kl_core  = "options ath6kl_core recovery_enable=1 heart_beat_poll=200"
module_conf_ath6kl_core += "btcoex_chip_type=2 btcoex_ant_config=4"
module_conf_ath6kl_core += "disable_fw_dbglog=1 suspend_mode=1"

KERNEL_MODULE_PROBECONF += "ath6kl_core"

require backports-laird.inc radio-stack-4550-version.inc
