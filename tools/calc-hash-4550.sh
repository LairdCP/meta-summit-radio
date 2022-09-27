[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

file="radio-stack-4550-hashes.inc"
prefix="https://files.devops.rfpros.com/builds/linux"
ver=${1}

calc_file () {
  name=${1##*/}
  wget ${1} || exit 1
  echo "SRC_URI[${2}.md5sum] = \"$(md5sum ${name} | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${2}.sha256sum] = \"$(sha256sum ${name} | awk '{print $1}')\"" >> ${file}
  rm -f ${name}
}

echo -e "PV = \"${ver}\"\n" > ${file}

for i in arm-eabi arm-eabihf
do
  calc_file "${prefix}/summit_supplicant/laird/${ver}/summit_supplicant_legacy-${i}-${ver}.tar.bz2" "summit-supplicant_legacy-${i}"
  calc_file "${prefix}/summit_supplicant/laird/${ver}/summit_supplicant_legacy_openssl_3_0-${i}-${ver}.tar.bz2" "summit-supplicant_legacy_openssl_3_0-${i}"
done

calc_file "${prefix}/backports/laird/${ver}/backports-summit-${ver}.tar.bz2" "summit-backports"
calc_file "${prefix}/firmware/${ver}/laird-ath6k-6003-firmware-${ver}.tar.bz2" "ath6k-6003-firmware"
calc_file "${prefix}/firmware/${ver}/laird-ath6k-6004-firmware-${ver}.tar.bz2" "ath6k-6004-firmware"
