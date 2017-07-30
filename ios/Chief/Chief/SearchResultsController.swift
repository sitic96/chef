//
//  SearchResultsController.swift
//  Chief
//
//  Created by Sitora on 21.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit
import CoreData
import UICircularProgressRing
import Alamofire


class SearchResultsController: UIViewController, UICircularProgressRingDelegate {

    @IBOutlet var ring1: UICircularProgressRingView!
    @IBOutlet var img: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        ring1.animationStyle = kCAMediaTimingFunctionLinear
        //ring1.font = UIFont.systemFont(ofSize: 70)
        ring1.delegate = self
        loadImage()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func finishedUpdatingProgress(forRing ring: UICircularProgressRingView) {
        if ring === ring1 {
            print("From delegate: Ring 1 finished")
        }
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//         Get the new view controller using segue.destinationViewController.
//         Pass the selected object to the new view controller.
    }

    func loadImage(){
        Alamofire.request("http://papus666.narod.ru/clipart/s/starm/oldman098.png")
            .downloadProgress {progress in self.controlProgress(value: Int(progress.fractionCompleted*100))
            }
            .responseData { response in
                if let data = response.result.value {
                    let image = UIImage(data: data)
                    self.img.image=image
                }
        }
    }
    
    func controlProgress(value:Int){
        ring1.setProgress(value: CGFloat(value), animationDuration: 0)
        if value>=100 {
            ring1.isHidden=true
        }
    }
}
