[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

ver=${1}

file="radio-stack-4550-hashes.inc"
prefix="https://files.devops.rfpros.com/builds/linux"

calc_file () {
  name=${1##*/}
  wget ${prefix}/${1}/${ver}/${2} || exit 1
  echo "SRC_URI[${3}.md5sum] = \"$(md5sum ${2} | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${3}.sha256sum] = \"$(sha256sum ${2} | awk '{print $1}')\"" >> ${file}
  rm -f ${2}
}

echo -e "PV = \"${ver}\"\n" > ${file}

for i in arm-eabi arm-eabihf
do
  calc_file "summit_supplicant/laird" "summit_supplicant_libs_legacy-${i}-${ver}.tar.bz2" "summit-supplicant-libs-legacy-${i}"
done

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "backports/laird" "backports-laird-${ver}.tar.bz2" "summit-backports"
calc_file "firmware" "laird-ath6k-6003-firmware-${ver}.tar.bz2" "ath6k-6003-firmware"
calc_file "firmware" "laird-ath6k-6004-firmware-${ver}.tar.bz2" "ath6k-6004-firmware"
