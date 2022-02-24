(ns generative-art.the-wrong-way-to-draw-a-line
  (:require [quil.core :as q]
            [quil.middleware :as m]))

 
(defn draw-random []
    (let [step 10
          border-y 10
          ps (for [x (range 1 50)]
               {:x (* x step)
                :y (+ border-y
                      (q/random (- (q/height) 
                                   (* border-y 2))))})]
      (doseq [p (partition 2 1 ps)]
        (let [[{x1 :x, y1 :y} {x2 :x, y2 :y}] p]
          (q/line x1 y1 x2 y2)))))

(defn draw-smooth-random []
  (let [step 10
        make-point (fn [col n]
                      (let [y-step  (- (q/random 20) 10) ; range -10 to 10
                            y (+ (:y (last col)) 
                                 y-step)]
                        (conj col {:x n :y y}))) 
        ps (reduce make-point
                   [{:x 0 :y (/ (q/height) 2)}]
                   (range 10 (q/width) step))]
    (doseq [p (partition 2 1 ps)]
      (let [[{x1 :x, y1 :y} {x2 :x, y2 :y}] p]
        (q/line x1 y1 x2 y2)))))


(defn setup []
  (q/frame-rate 30)
  ;(q/color-mode :hsb)
  (q/background 255)
  (q/stroke-weight 1)
  (q/smooth)

  (q/stroke 20 50 70)
  ;(draw-random)
  (draw-smooth-random)
  )

(defn update-state [state])

(defn draw-state [state])

(q/defsketch generative-art
  :title "the wrong way to draw a line"
  :size [500 100]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])