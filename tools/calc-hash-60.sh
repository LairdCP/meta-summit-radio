#! /bin/sh

dir=$(dirname "${0}")
ver=${1}

# shellcheck disable=SC2034
file="radio-stack-60-hashes.inc"

# shellcheck source=/dev/null
. "${dir}/calc-common.sh"

for i in x86 x86_64 arm-eabi arm-eabihf aarch64 powerpc64-e5500
do
  calc_file "adaptive_ww/laird" "adaptive_ww-${i}-${ver}.tar.bz2" "adaptive-ww-${i}"
  calc_file "summit_supplicant/laird" "summit_supplicant_libs-${i}-${ver}.tar.bz2" "summit-supplicant-libs-${i}"
done

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "adaptive_bt/src" "adaptive_bt-src-${ver}.tar.gz" "adaptive-bt"
calc_file "lrd-network-manager/src" "summit-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "backports/laird" "summit-backports-${ver}.tar.bz2" "summit-backports"

for i in pcie-uart pcie-usb sdio-uart sdio-sdio usb-usb usb-uart
do
  calc_file "firmware" "summit-60-radio-firmware-${i}-${ver}.tar.bz2" "60-radio-firmware-${i}"
done

calc_file "firmware" "summit-som8mp-radio-firmware-${ver}.tar.bz2" "som8mp-radio-firmware"
