
(def politician1 {
   :charisma 45.0
   :issues 10.0
   :familiarity 45.0 })

(def politician2 {
   :charisma 12.0
   :issues 75.0 
   :familiarity 13.0 })

(def voter1 {
   :charisma 0.50
   :issues 0.0
   :familiarity 0.50
   })

(defn score [voter politician]
 ( + ( * ( voter :charisma) ( politician :charisma))
     ( * ( voter :issues) ( politician :issues))
     ( * ( voter :familiarity) ( politician :familiarity))
    ))
  
(def v1_p1 
  (score voter1 politician1)
)
(def v1_p2 
  (score voter1 politician2)
)
(defn vote [voter politicians]
 (score voter (first politicians)) 
 (recur score [voter (next politicians)])
)

(vote voter1 [politician1, politician2])

