//
//  colvwCell.swift
//  Chief
//
//  Created by Sitora on 11.07.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit

class colvwCell: UICollectionViewCell {
    @IBOutlet var label: UILabel!
    @IBOutlet var gifView: UIImageView!
    @IBOutlet var likeButton: UIButton!
    @IBOutlet var saveButton: UIButton!
    
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
    
}
