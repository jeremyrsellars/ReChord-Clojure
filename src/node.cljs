(ns helloserver
  (:require [cljs.nodejs :as nodejs]
            [rechord.core :as core]
            [rechord.linereader :as linereader]
            [rechord.main :as main]))
 
(defn -main [& mess]
  (set! module.exports.main main/rechord)
  (set! module.exports.preferSharps core/prefer-sharps)
  (set! module.exports.preferFlats core/prefer-flats)
  )
 
(set! *main-cli-fn* -main)
