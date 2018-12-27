(ns re-frame-spec-forms-doc.model
  (:require [cljs.spec.alpha :as s]))

(s/def ::non-empty-string (s/and string?
                                 (complement empty?)))

(s/def ::first-name ::non-empty-string)
(s/def ::last-name ::non-empty-string)
(s/def ::line1 ::non-empty-string)
(s/def ::line2 (s/nilable string?))
(s/def ::city ::non-empty-string)
(s/def ::zip pos-int?)

(def SEXES (list "male" "female"))
(s/def ::sex (set SEXES))

(s/def ::address
  (s/keys :res-un [::line1 ::city ::zip]
          :opt-un [::line2]))



(s/def ::registration-form
  (s/keys :req-un
          [::first-name ::last-name ::sex ::address]))


(def state-abbrs
  (list
    {:label "Alabama" :abbr "AL"}
    {:label "Alaska" :abbr "AK"}
    {:label "Arizona" :abbr "AZ"}
    {:label "Arkansas" :abbr "AR"}
    {:label "California" :abbr "CA"}
    {:label "Colorado" :abbr "CO"}
    {:label "Connecticut" :abbr "CT"}
    {:label "Delaware" :abbr "DE"}
    {:label "Florida" :abbr "FL"}
    {:label "Georgia" :abbr "GA"}
    {:label "Hawaii" :abbr "HI"}
    {:label "Idaho" :abbr "ID"}
    {:label "Illinois" :abbr "IL"}
    {:label "Indiana" :abbr "IN"}
    {:label "Iowa" :abbr "IA"}
    {:label "Kansas" :abbr "KS"}
    {:label "Kentucky" :abbr "KY"}
    {:label "Louisiana" :abbr "LA"}
    {:label "Maine" :abbr "ME"}
    {:label "Maryland" :abbr "MD"}
    {:label "Massachusetts" :abbr "MA"}
    {:label "Michigan" :abbr "MI"}
    {:label "Minnesota" :abbr "MN"}
    {:label "Mississippi" :abbr "MS"}
    {:label "Missouri" :abbr "MO"}
    {:label "Montana" :abbr "MT"}
    {:label "Nebraska" :abbr "NE"}
    {:label "Nevada" :abbr "NV"}
    {:label "New Hampshire" :abbr "NH"}
    {:label "New Jersey" :abbr "NJ"}
    {:label "New Mexico" :abbr "NM"}
    {:label "New York" :abbr "NY"}
    {:label "North Carolina" :abbr "NC"}
    {:label "North Dakota" :abbr "ND"}
    {:label "Ohio" :abbr "OH"}
    {:label "Oklahoma" :abbr "OK"}
    {:label "Oregon" :abbr "OR"}
    {:label "Pennsylvania" :abbr "PA"}
    {:label "Rhode Island" :abbr "RI"}
    {:label "South Carolina" :abbr "SC"}
    {:label "South Dakota" :abbr "SD"}
    {:label "Tennessee" :abbr "TN"}
    {:label "Texas" :abbr "TX"}
    {:label "Utah" :abbr "UT"}
    {:label "Vermont" :abbr "VT"}
    {:label "Virginia" :abbr "VA"}
    {:label "Washington" :abbr "WA"}
    {:label "West Virginia" :abbr "WV"}
    {:label "Wisconsin" :abbr "WI"}
    {:label "Wyoming" :abbr "WY"}))
