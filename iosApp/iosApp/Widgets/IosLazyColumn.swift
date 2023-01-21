//
//  IosLazyColumn.swift
//  iosApp
//
//  Created by alobynya on 20.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosLazyColumn: MySuperLazyColumn {
    
    private let dataSource = CompouseDataSource()
    
    private let root: UITableView = {
        let container = UITableView()
        container.translatesAutoresizingMaskIntoConstraints = true
        container.rowHeight = UITableView.automaticDimension
        container.estimatedRowHeight = UITableView.automaticDimension
        container.allowsSelection = false
        container.dataSource = dataSource
        return container;
    }()
    var counter = 1
    
    func childs(childs: [Redwood_widgetWidgetChildren]) {
        counter = counter + 1
    }
    
    func childs2(childs: [@convention(block) () -> KotlinUnit?]) {
        counter = counter + 2)
        let redwoodContent = RedwoodComposeContent(
   }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
 
    var value: Any { root }
    
    
}

class CompouseDataSource: UIViewController, UITableViewDataSource {
    
    private let widgetFactory = IosWidgetFactory()
    
    var data: [@convention(block) () -> KotlinUnit?] = []
    
    func setData(newList: [@convention(block) () -> KotlinUnit?]){
        data = newList
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:MyCustomTableViewCell = MyCustomTableViewCell()
          
        let compouse = data[indexPath.row]
        
        
        let delegate = RedwoodViewControllerDelegate(
            root: cell.container,
            widgetFactory: widgetFactory
        )
        return cell
    }
}

class MyCustomTableViewCell: UITableViewCell{
    let container = UIStackView()

       override func awakeFromNib() {
           super.awakeFromNib()
           container.translatesAutoresizingMaskIntoConstraints = true
           contentView.addSubview(container)
           container.backgroundColor = .white
           container.axis = .vertical
           container.alignment = .fill
           container.distribution = .fill
           container.translatesAutoresizingMaskIntoConstraints = false

           container.leadingAnchor.constraint(equalTo: contentView.safeAreaLayoutGuide.leadingAnchor).isActive = true
           container.topAnchor.constraint(equalTo: contentView.safeAreaLayoutGuide.topAnchor).isActive = true
           container.widthAnchor.constraint(equalTo: contentView.safeAreaLayoutGuide.widthAnchor).isActive = true
           container.bottomAnchor.constraint(equalTo: contentView.safeAreaLayoutGuide.bottomAnchor).isActive = true
       }

}
