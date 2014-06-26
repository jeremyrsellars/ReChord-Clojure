(ns rechord.noderechord
  (:require [cljs.nodejs :as nodejs]
            [rechord.core :as core]
            [rechord.linereader :as linereader]
            [rechord.htmlrenderer :as htmlrenderer]
            [rechord.main :as main]))

(defn -main [& mess]
  (set! module.exports.rechord main/rechord)
  (set! module.exports.rechordHtml main/rechord-html)
  (set! module.exports.rechordTaggedLines main/rechord-tagged-lines)

  (set! module.exports.getTaggedLines (fn [text] (to-array (map to-array (linereader/get-tagged-lines text)))))
  (set! module.exports.getLineType linereader/get-line-type)

  (set! module.exports.preferSharps core/prefer-sharps)
  (set! module.exports.preferFlats core/prefer-flats)
  )

(set! *main-cli-fn* -main)


