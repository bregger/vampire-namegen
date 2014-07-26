(ns vampire-web.namegen)

(defn- psnr
  "Linear congruential generator with parameters taken from glibc"
  [prev]
  (mod (+ 12345 (* prev 1103515245)) 2147483648))

(defn- generate-seed
  "Transforms the input string into a seed for the psnr"
  [human-name]
  (->> (vec (str "My name is " human-name " and I love vampires!"))
       (map int)
       (map * (range))
       (reduce +)))

(def ^:private names
  ["Domenico" "Bryce" "Cain" "Eli" "Giovanni" "Kristopher" "Nicholai"
   "Sirius" "Xavier" "Angelus" "Christian" "Julius" "Theron" "Virgil"
   "Isaac" "Julian" "Solomon" "Reyes" "Raul" "Rodolfo" "Alessandro"
   "Edwin" "Warren" "Duradel" "Wolfram" "Morgan" "Hunter" "Edward"
   "Drake" "Horatio" "Sterling" "Sebastian" "Alucard"])

(def ^:private surname1
  ["Cull" "Blood" "Mor" "Morg" "Van" "Goss" "Nos" "Black" "Beau"
   "San" "Moon" "Night" "Locke" "God" "Grim" "Grief" "Long" "Le"])

(def ^:private surname2
  ["mont" "hart" "ric" "fang" "ren" "fel" "mer" "imer" "gloom" "blanc"
   "bane" "isses" "rik" "erit" "anger" "zel" "cia" "man" "sen"])

(defn- mod-nth
  [col n]
  (nth col (mod n (count col))))

(defn vampire-name
  "Given a human name calculates a random vampire name!"
  [human-name]
  (let [seed (generate-seed human-name)
        r1 (psnr seed)
        r2 (psnr r1)
        r3 (psnr r2)]
    (str (mod-nth names r1)
         " "
         (mod-nth surname1 r2) (mod-nth surname2 r3))))
