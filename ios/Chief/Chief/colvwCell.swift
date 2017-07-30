//
//  colvwCell.swift
//  Chief
//
//  Created by Sitora on 11.07.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit
import UICircularProgressRing

class colvwCell: UICollectionViewCell {
    @IBOutlet var label: UILabel!
    @IBOutlet var gifView: UIImageView!
    @IBOutlet var likeButton: UIButton!
    @IBOutlet var saveButton: UIButton!
    @IBOutlet var user_name: UILabel!
    @IBOutlet var profile_picture: UIImageView!
    @IBOutlet var progressRing: UICircularProgressRingView!
    
    var isLiked : Bool? = false
    var isSaved : Bool? = false
    
    // TODO: Добавить логику после изменения бд
    
    @IBAction func likeButtonClicked(_ sender: Any) {
        if isLiked! {
            likeButton.setImage(UIImage(named: "ic_favorite_border"), for: .normal)
            isLiked = false
        } else {
            likeButton.setImage(UIImage(named: "ic_favorite"), for: .normal)
            isLiked = true
        }
    }
    
    @IBAction func saveButtonClicked(_ sender: Any) {
        if isSaved! {
            saveButton.setImage(UIImage(named: "ic_arrow_downward"), for: .normal)
            isSaved = false
        } else {
            saveButton.setImage(UIImage(named: "ic_save"), for: .normal)
            isSaved = true
        }
    }
    
    func controlProgress(value:Int){
        progressRing.setProgress(value: CGFloat(value), animationDuration: 0)
        if value>=100 {
            progressRing.isHidden=true
        }
    }
    
}
