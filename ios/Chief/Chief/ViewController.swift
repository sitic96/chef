//
//  ViewController.swift
//  Chief
//
//  Created by Sitora on 11.07.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit
import Foundation
import SwiftyJSON
import SwiftGifOrigin
import SwiftyGif

class ViewController: UIViewController, UICollectionViewDataSource, UICollectionViewDelegate {
    

    @IBOutlet var collectionView: UICollectionView!
    
    var recipes = [CompleteRecipe]()
    var recipe : CompleteRecipe?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getRandomRecipes()
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return recipes.count
    }
    
    // TODO: Заменить на SwiftyGif
    // TODO: После изменений бд заменить на реальные гифки
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell: colvwCell = collectionView.dequeueReusableCell(withReuseIdentifier: "Cell", for: indexPath) as! colvwCell
        cell.label.text = recipes[indexPath.row].recipe_name
        cell.user_name.text = recipes[indexPath.row].user_name
        
        setGifImage(cell:cell, index:indexPath.row)
        setProfileImage(cell: cell, index: indexPath.row)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let cell = collectionView.cellForItem(at: indexPath) as? colvwCell{
            
            if cell.gifView.isAnimatingGif() {
                cell.gifView.stopAnimatingGif()
            }
            else {
                cell.gifView.startAnimatingGif()
            }
        }
        print("Cell \(indexPath.row) selected")
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func getRandomRecipes(){
        RestApiManager.sharedInstance.getRecipe { (json: JSON) in
            DispatchQueue.main.async(execute: {
                self.recipe = CompleteRecipe(json:json)
                self.recipes.append(self.recipe!)
            /*for (_, object) in json {
                
                self.recipes.append(Recipe(json: object))
            }
 */
                    self.collectionView.reloadData()
            })
        }
    }

    func setGifImage(cell:colvwCell, index:Int){
        let url = URL(string: recipes[index].img_link)
        let data = try? Data(contentsOf: url!)
        
        let gifmanager = SwiftyGifManager(memoryLimit:20)
        let img = UIImage(gifData: data!, levelOfIntegrity: 0.5)
        OperationQueue.main.addOperation {
            cell.gifView.setGifImage(img, manager: gifmanager, loopCount:5)
        }
    }
    
    func setProfileImage(cell:colvwCell, index:Int) {
        let url = URL(string: recipes[index].profile_picture)
        let data = try? Data(contentsOf: url!)
        OperationQueue.main.addOperation {
            cell.profile_picture.layer.cornerRadius = 20;
            cell.profile_picture.layer.masksToBounds = true;
            cell.profile_picture.image = UIImage(data: data! as Data)
        }
    }
    
}
