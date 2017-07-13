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
    
    var recipes = [Recipe]()
    
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
        cell.label.text = recipes[indexPath.row].name
        
        let url = URL(string: "http://orig03.deviantart.net/c7a3/f/2012/258/9/1/ani_rainbow_by_engineerjr-d5et1sk.gif")
        let data = try? Data(contentsOf: url!)
        
        let gifmanager = SwiftyGifManager(memoryLimit:20)
        let img = UIImage(gifData: data!, levelOfIntegrity: 0.5)
        OperationQueue.main.addOperation {
            cell.gifView.setGifImage(img, manager: gifmanager, loopCount:2)
        }
        
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
        RestApiManager.sharedInstance.getRandomRecipes { (json: JSON) in
            DispatchQueue.main.async(execute: {

            for (_, object) in json {
                
                self.recipes.append(Recipe(json: object))
            }
                    self.collectionView.reloadData()
            })
        }
    }
    
    func setButtonImages(){
        
    }
}
