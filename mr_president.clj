
(def politician1 {
   :id 1
   :charisma 45.0
   :issues 10.0
   :familiarity 45.0 })

(def politician2 {
   :id 2
   :charisma 12.0
   :issues 75.0 
   :familiarity 13.0 })

(def voter1 {
   :id 1
   :charisma 0.50
   :issues 0.0
   :familiarity 0.50
   })

(def voter2 {
   :id 2
   :charisma 0.50
   :issues 0.0
   :familiarity 0.50
   })

(defn sort_by_score [a b]
 ( > (a :score) (b :score))
)

(defn score [voter politician]
 ( + ( * ( voter :charisma) ( politician :charisma))
     ( * ( voter :issues) ( politician :issues))
     ( * ( voter :familiarity) ( politician :familiarity))
    ))
  
(defn vote [voter politicians]
 (defn v_score [p] ( merge p {:score (score voter p)}))
 (first (sort sort_by_score (map v_score politicians)))
)

(defn election [voters politicians]
 (defn v_vote [v] ( vote v politicians))
 (defn count_by_id [id p_list] (count (filter (fn [item] (= id (item :id))) p_list)))
 (defn politician_by_id [id] (filter (fn [item] (= id (item :id))) politicians))

 (let [results 
    (loop [ result [] vs voters ]
    (if (empty? vs)
     result
    (recur (conj result (v_vote (first vs))) (rest vs)))
   )] 
   (defn get_id [item] (item :id))
   (defn list-of-ids [] (map get_id politicians))
   (defn politician_with_votes [id] 
    (merge ( first (politician_by_id id) ) { :votes (count_by_id id results)})
   )
   (map politician_with_votes (distinct(list-of-ids)))
 )
)

(election [voter1 voter2] [politician1, politician2])

