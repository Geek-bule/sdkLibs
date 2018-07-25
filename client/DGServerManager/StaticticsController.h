//
//  StaticticsController.h
//  DGServerManager
//
//  Created by apple on 2018/3/26.
//  Copyright © 2018年 apple. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ASFTableView.h"

@interface StaticticsController : UIViewController

@property (weak, nonatomic) IBOutlet ASFTableView *mASFTableView;

- (void)initWithCode:(NSString*)code;

@end
